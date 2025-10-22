const API_URL = "http://localhost:8080/v1/movie";

const getMovies = (title, genre, page, rowsPerPage) => {
  const PARAMS =
    "?" +
    (title ? "title=" + title + "&" : "") +
    (genre ? "genre=" + genre + "&" : "") +
    (page ? "page=" + page + "&" : "") +
    (rowsPerPage ? "size=" + rowsPerPage + "&" : "");
  return fetch(API_URL + PARAMS);
};

export default { getMovies };
