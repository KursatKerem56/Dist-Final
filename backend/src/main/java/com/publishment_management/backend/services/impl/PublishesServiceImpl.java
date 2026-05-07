package com.publishment_management.backend.services.impl;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Service;

import com.publishment_management.backend.dtos.DtoPublishes;
import com.publishment_management.backend.dtos.DtoPublishesIU;
import com.publishment_management.backend.exceptions.BaseException;
import com.publishment_management.backend.exceptions.ErrorMessage;
import com.publishment_management.backend.exceptions.MessageType;
import com.publishment_management.backend.mappers.PublishesMapper;
import com.publishment_management.backend.models.Publishes;
import com.publishment_management.backend.repositories.PublishesRepository;
import com.publishment_management.backend.services.IPublishesService;

import lombok.RequiredArgsConstructor;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;


@Service
@RequiredArgsConstructor
public class PublishesServiceImpl implements IPublishesService {
	
	private final PublishesRepository publishesRepository;	
	private final PublishesMapper publishesMapper;

	@Override
	public DtoPublishes getPublishesById(Integer id) {
		return publishesMapper.toDto(publishesRepository.findById(id).
				orElseThrow(() -> new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST, null))));
	}

	@Override
	public List<DtoPublishes> getPublishesList() {
		return publishesMapper.toDtoList(publishesRepository.findAll());
	}

	@Override
	public DtoPublishes savePublishes(DtoPublishesIU publishes) {
		return publishesMapper.toDto(publishesRepository.save(publishesMapper.toEntity(publishes)));
	}

	@Override
	public void deletePublishes(Integer id) {
		
		Publishes existingPublishes = publishesRepository.findById(id).
				orElseThrow(() -> new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST, null)));
		
		publishesRepository.delete(existingPublishes);
	}

	@Override
	public DtoPublishes changePublishes(Integer id, DtoPublishesIU changedPublishes) {
		
		Publishes existingPublishes = publishesRepository.findById(id).
				orElseThrow(() -> new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST, null)));
		
		publishesMapper.copyPublishesFromDtoIU(changedPublishes, existingPublishes);
		
		return publishesMapper.toDto(publishesRepository.save(existingPublishes));
	}

	@Override
	public byte[] createPublishesPdfReport() {
		try {
			List<DtoPublishes> publishesList = getPublishesList();

			InputStream reportStream = getClass().getResourceAsStream("/reports/publishes_report.jrxml");

			if (reportStream == null) {
				throw new RuntimeException("Jasper template not found: /reports/publishes_report.jrxml");
			}

			JasperReport jasperReport = JasperCompileManager.compileReport(reportStream);
			JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(publishesList);

			JasperPrint jasperPrint = JasperFillManager.fillReport(
					jasperReport,
					new HashMap<>(),
					dataSource
			);

			return JasperExportManager.exportReportToPdf(jasperPrint);
		} catch (Exception e) {
			throw new RuntimeException("Jasper PDF report could not be created", e);
		}
	}

}
