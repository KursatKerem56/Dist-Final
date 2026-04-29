import React from "react";
import ReactDOM from "react-dom/client";
import "./style.css";

function App() {
  return (
    <main className="page">
      <section className="card">
        <p className="eyebrow">React + Vite</p>
        <h1>Node-based React server is ready.</h1>
        <p className="copy">
          This client is running on Vite and can talk to the Node backend at{" "}
          <code>/api/health</code>.
        </p>
        <div className="buttons">
          <a
            className="button primary"
            href="http://localhost:3001/api/health"
            target="_blank"
            rel="noreferrer"
          >
            Check backend
          </a>
        </div>
      </section>
    </main>
  );
}

ReactDOM.createRoot(document.getElementById("root")).render(
  <React.StrictMode>
    <App />
  </React.StrictMode>,
);
