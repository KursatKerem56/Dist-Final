import { useEffect, useState } from "react";
import api from "../services/api";

export default function Authors() {
  const [authors, setAuthors] = useState([]);
  const [form, setForm] = useState({
    name: "",
    address: "",
    image: "",
  });

  const loadAuthors = async () => {
    const response = await api.get("/author/list");
    setAuthors(response.data.data);
  };

  useEffect(() => {
    loadAuthors();
  }, []);

  const handleImageChange = (e) => {
    const file = e.target.files[0];

    if (!file) return;

    const reader = new FileReader();

    reader.onloadend = () => {
      setForm({ ...form, image: reader.result });
    };

    reader.readAsDataURL(file);
  };

  const handleSubmit = async (e) => {
    e.preventDefault();

    await api.post("/author/save", form);

    setForm({
      name: "",
      address: "",
      image: "",
    });

    loadAuthors();
  };

  return (
    <section>
      <h1>Authors</h1>

      <form onSubmit={handleSubmit}>
        <input
          type="text"
          placeholder="Author name"
          value={form.name}
          onChange={(e) => setForm({ ...form, name: e.target.value })}
          required
        />

        <input
          type="text"
          placeholder="Address"
          value={form.address}
          onChange={(e) => setForm({ ...form, address: e.target.value })}
          required
        />

        <input type="file" accept="image/*" onChange={handleImageChange} />

        <button type="submit">Add Author</button>
      </form>

      {form.image && (
        <img
          src={form.image}
          alt="Preview"
          style={{ width: "120px", marginTop: "15px" }}
        />
      )}

      <table>
        <thead>
          <tr>
            <th>ID</th>
            <th>Image</th>
            <th>Author Name</th>
            <th>Address</th>
          </tr>
        </thead>

        <tbody>
          {authors.map((author) => (
            <tr key={author.id}>
              <td>{author.id}</td>
              <td>
                {author.image && (
                  <img
                    src={author.image}
                    alt={author.name}
                    style={{ width: "70px", height: "70px", objectFit: "cover" }}
                  />
                )}
              </td>
              <td>{author.name}</td>
              <td>{author.address}</td>
            </tr>
          ))}
        </tbody>
      </table>
    </section>
  );
}