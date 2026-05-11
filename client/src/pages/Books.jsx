import { useEffect, useState } from "react";
import api from "../services/api";

export default function Books() {
  const [books, setBooks] = useState([]);
  const [editingId, setEditingId] = useState(null);
  const [form, setForm] = useState({
    title: "",
    publisher: "",
  });

  const clearForm = () => {
    setForm({
      title: "",
      publisher: "",
    });
  };

  const loadBooks = async () => {
    const response = await api.get("/book/list");
    setBooks(response.data.data);
  };

  useEffect(() => {
    loadBooks();
  }, []);

  const handleSubmit = async (e) => {
    e.preventDefault();

    if (editingId) {
      await api.put(`/book/change/${editingId}`, form);
    } else {
      await api.post("/book/save", form);
    }

    clearForm();
    setEditingId(null);
    loadBooks();
  };

  const handleEdit = (book) => {
    setEditingId(book.id);
    setForm({
      title: book.title,
      publisher: book.publisher,
    });
  };

  const handleDelete = async (id) => {
    if (!window.confirm("Are you sure you want to delete this book?")) return;

    await api.delete(`/book/delete/${id}`);
    clearForm();
    setEditingId(null);
    loadBooks();
  };

  const cancelEdit = () => {
    setEditingId(null);
    clearForm();
  };

  return (
    <section className="section">
      <h1>Books</h1>

      <form onSubmit={handleSubmit}>
        <input
          type="text"
          placeholder="Book title"
          value={form.title}
          onChange={(e) => setForm({ ...form, title: e.target.value })}
          required
        />

        <input
          type="text"
          placeholder="Publisher"
          value={form.publisher}
          onChange={(e) => setForm({ ...form, publisher: e.target.value })}
          required
        />

        <button type="submit">{editingId ? "Update Book" : "Add Book"}</button>

        {editingId && (
          <button type="button" onClick={cancelEdit}>
            Cancel
          </button>
        )}
      </form>

      <table>
        <thead>
          <tr>
            <th>ID</th>
            <th>Book Title</th>
            <th>Publisher</th>
            <th>Actions</th>
          </tr>
        </thead>

        <tbody>
          {books.map((book) => (
            <tr key={book.id}>
              <td>{book.id}</td>
              <td>{book.title}</td>
              <td>{book.publisher}</td>
              <td>
                <button type="button" onClick={() => handleEdit(book)}>
                  Edit
                </button>

                <button
                  type="button"
                  onClick={() => handleDelete(book.id)}
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
