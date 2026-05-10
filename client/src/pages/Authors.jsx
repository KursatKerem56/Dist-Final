import { useEffect, useState } from "react";
import api from "../services/api";

export default function Authors() {
  const [authors, setAuthors] = useState([]);
  const [editingId, setEditingId] = useState(null);

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

    if (editingId) {
      await api.put(`/author/change/${editingId}`, form);
    } else {
      await api.post("/author/save", form);
    }

    setForm({
      name: "",
      address: "",
      image: "",
    });

    setEditingId(null);
    loadAuthors();
  };

  const handleEdit = (author) => {
    setEditingId(author.id);
    setForm({
      name: author.name,
      address: author.address,
      image: author.image || "",
    });
  };

  const handleDelete = async (id) => {
    if (!window.confirm("Are you sure you want to delete this author?")) return;

    await api.delete(`/author/delete/${id}`);
    loadAuthors();
  };

  const cancelEdit = () => {
    setEditingId(null);
    setForm({
      name: "",
      address: "",
      image: "",
    });
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

        <button type="submit">
          {editingId ? "Update Author" : "Add Author"}
        </button>

        {editingId && (
          <button type="button" onClick={cancelEdit}>
            Cancel
          </button>
        )}
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
            <th>Actions</th>
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
                    style={{
                      width: "70px",
                      height: "70px",
                      objectFit: "cover",
                    }}
                  />
                )}
              </td>
              <td>{author.name}</td>
              <td>{author.address}</td>
              <td>
                <button type="button" onClick={() => handleEdit(author)}>
                  Edit
                </button>

                <button
                  type="button"
                  onClick={() => handleDelete(author.id)}
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