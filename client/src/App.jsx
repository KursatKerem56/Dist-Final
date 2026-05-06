import { Routes, Route, Navigate } from "react-router-dom";
import Navbar from "./components/Navbar";
import Authors from "./pages/Authors";
import Books from "./pages/Books";
import Publishes from "./pages/Publishes";

function App() {
  return (
    <>
      <Navbar />
      <main className="container">
        <Routes>
          <Route path="/" element={<Navigate to="/authors" />} />
          <Route path="/authors" element={<Authors />} />
          <Route path="/books" element={<Books />} />
          <Route path="/publishes" element={<Publishes />} />
        </Routes>
      </main>
    </>
  );
}

export default App;