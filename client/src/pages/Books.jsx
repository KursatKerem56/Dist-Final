import { useEffect, useState } from "react";
import api from "../services/api";

export default function Books() {
  const [books, setBooks] = useState([]);
  const [form, setForm] = useState({
    title: "",
    publisher: "",
  });

  const loadBooks = async () => {
    const response = await api.get("/book/list");
    setBooks(response.data.data);
  };

  useEffect(() => {
    loadBooks();
  }, []);

  const handleSubmit = async (e) => {
    e.preventDefault();

    await api.post("/book/save", form);

    setForm({
      title: "",
      publisher: "",
    });

    loadBooks();
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

        <button type="submit">Add Book</button>
      </form>

      <table>
        <thead>
          <tr>
            <th>ID</th>
            <th>Book Title</th>
            <th>Publisher</th>
          </tr>
        </thead>

        <tbody>
          {books.map((book) => (
            <tr key={book.id}>
              <td>{book.id}</td>
              <td>{book.title}</td>
              <td>{book.publisher}</td>
            </tr>
          ))}
        </tbody>
      </table>
    </section>
  );
}