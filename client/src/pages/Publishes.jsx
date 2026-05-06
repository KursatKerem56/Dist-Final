import { useEffect, useState } from "react";
import api from "../services/api";
import jsPDF from "jspdf";
import autoTable from "jspdf-autotable";

export default function Publishes() {
  const [publishes, setPublishes] = useState([]);
  const [authors, setAuthors] = useState([]);
  const [books, setBooks] = useState([]);

  const [form, setForm] = useState({
    authorId: "",
    bookId: "",
    edition: "",
  });

  const loadData = async () => {
    const publishesRes = await api.get("/publishes/list");
    const authorsRes = await api.get("/author/list");
    const booksRes = await api.get("/book/list");

    setPublishes(publishesRes.data.data);
    setAuthors(authorsRes.data.data);
    setBooks(booksRes.data.data);
  };

  useEffect(() => {
    loadData();
  }, []);

  const handleSubmit = async (e) => {
    e.preventDefault();

    await api.post("/publishes/save", {
      ...form,
      edition: Number(form.edition),
      authorId: Number(form.authorId),
      bookId: Number(form.bookId),
    });

    setForm({
      authorId: "",
      bookId: "",
      edition: "",
    });

    loadData();
  };
const createPdfReport = () => {
  const doc = new jsPDF();

  doc.text("Publishes Report", 14, 15);

  autoTable(doc, {
    startY: 25,
    head: [["ID", "Author", "Book", "Edition", "Added Date"]],
    body: publishes.map((publish) => [
      publish.id,
      publish.authorName,
      publish.bookTitle,
      publish.edition,
      publish.addedDate,
    ]),
  });

  doc.save("publishes-report.pdf");
};
  return (
    <section>
      <h1>Publishes</h1>

      <form onSubmit={handleSubmit}>
        <select
          value={form.authorId}
          onChange={(e) =>
            setForm({ ...form, authorId: e.target.value })
          }
          required
        >
          <option value="">Select Author</option>

          {authors.map((author) => (
            <option key={author.id} value={author.id}>
              {author.name}
            </option>
          ))}
        </select>

        <select
          value={form.bookId}
          onChange={(e) =>
            setForm({ ...form, bookId: e.target.value })
          }
          required
        >
          <option value="">Select Book</option>

          {books.map((book) => (
            <option key={book.id} value={book.id}>
              {book.title}
            </option>
          ))}
        </select>

        <input
          type="number"
          placeholder="Edition"
          value={form.edition}
          onChange={(e) =>
            setForm({ ...form, edition: e.target.value })
          }
          required
        />

        <button type="submit">Add Publish</button>
      </form>
<button type="button" onClick={createPdfReport}>
  Create PDF Report
</button>
      <table>
        <thead>
          <tr>
            <th>ID</th>
            <th>Author</th>
            <th>Book</th>
            <th>Edition</th>
            <th>Added Date</th>
          </tr>
        </thead>

        <tbody>
          {publishes.map((publish) => (
            <tr key={publish.id}>
              <td>{publish.id}</td>
              <td>{publish.authorName}</td>
              <td>{publish.bookTitle}</td>
              <td>{publish.edition}</td>
              <td>{publish.addedDate}</td>
            </tr>
          ))}
        </tbody>
      </table>
    </section>
  );
}