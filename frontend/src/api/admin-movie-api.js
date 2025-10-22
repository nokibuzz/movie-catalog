import axios from "axios";
import authHeader from "../helper/auth-header";

const API_URL = "http://localhost:8080/v1/admin/movie";

const getMovies = (title, genre, page, rowsPerPage) => {
  const PARAMS =
    "?" +
    (title ? "title=" + title + "&" : "") +
    (genre ? "genre=" + genre + "&" : "") +
    (page ? "page=" + page + "&" : "") +
    (rowsPerPage ? "size=" + rowsPerPage + "&" : "");
  return fetch(API_URL + PARAMS);
};

const addMovie = (movie) => {
  return axios.post(API_URL, movie, { headers: authHeader() });
};

const updateMovie = (id, movie) => {
  return axios.patch(`${API_URL}/${id}`, movie, { headers: authHeader() });
};

const deleteMovie = (id) => {
  return axios.delete(`${API_URL}/${id}`, { headers: authHeader() });
};

export default {
  addMovie,
  getMovies,
  updateMovie,
  deleteMovie,
};
