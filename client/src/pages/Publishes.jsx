import { useEffect, useState } from "react";
import api from "../services/api";

export default function Publishes() {
  const [publishes, setPublishes] = useState([]);
  const [authors, setAuthors] = useState([]);
  const [books, setBooks] = useState([]);
  const [editingId, setEditingId] = useState(null);

  const [form, setForm] = useState({
    authorId: "",
    bookId: "",
    edition: "",
  });

  const clearForm = () => {
    setForm({
      authorId: "",
      bookId: "",
      edition: "",
    });
  };

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

    if (editingId) {
      await api.put(`/publishes/change/${editingId}`, {
        ...form,
        edition: Number(form.edition),
        authorId: Number(form.authorId),
        bookId: Number(form.bookId),
      });
    } else {
      await api.post("/publishes/save", {
        ...form,
        edition: Number(form.edition),
        authorId: Number(form.authorId),
        bookId: Number(form.bookId),
      });
    }

    clearForm();
    loadData();
  };

  const createPdfReport = async () => {
    const response = await api.get("/publishes/pdf", {
      responseType: "blob",
    });

    const url = window.URL.createObjectURL(new Blob([response.data]));
    const link = document.createElement("a");

    link.href = url;
    link.setAttribute("download", "publishes-report.pdf");
    document.body.appendChild(link);
    link.click();
    link.remove();
  };

  const handleEdit = (publish) => {
    setEditingId(publish.id);
    setForm({
      authorId: publish.authorId,
      bookId: publish.bookId,
      edition: publish.edition,
    });
  };

  const handleDelete = async (id) => {
    if (!window.confirm("Are you sure you want to delete this publish?"))
      return;

    await api.delete(`/publishes/delete/${id}`);
    clearForm();
    setEditingId(null);
    loadData();
  };

  const cancelEdit = () => {
    setEditingId(null);
    clearForm();
  };

  return (
    <section>
      <h1>Publishes</h1>

      <form onSubmit={handleSubmit}>
        <select
          value={form.authorId}
          onChange={(e) => setForm({ ...form, authorId: e.target.value })}
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
          onChange={(e) => setForm({ ...form, bookId: e.target.value })}
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
          onChange={(e) => setForm({ ...form, edition: e.target.value })}
          required
        />

        <button type="submit">{editingId ? "Update" : "Add"} Publish</button>

        {editingId && (
          <button type="button" onClick={cancelEdit}>
            Cancel
          </button>
        )}
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
            <th>Actions</th>
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
              <td>
                <button type="button" onClick={() => handleEdit(publish)}>
                  Edit
                </button>

                <button
                  type="button"
                  onClick={() => handleDelete(publish.id)}
                  style={{ marginLeft: "8px", background: "#ef4444" }}
                >
                  Delete
                </button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </section>
  );
}
