import { Link } from "react-router-dom";

export default function Navbar() {
  return (
    <nav
      style={{
        display: "flex",
        gap: "20px",
        padding: "20px",
        background: "#111827",
      }}
    >
      <Link to="/authors">Authors</Link>
      <Link to="/books">Books</Link>
      <Link to="/publishes">Publishes</Link>
    </nav>
  );
}