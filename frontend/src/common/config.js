export const API_URL =
  process.env.NODE_ENV == "development"
    ? "http://localhost:8080"
    : "http://localhost:8080";
