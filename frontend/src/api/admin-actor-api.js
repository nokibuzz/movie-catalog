import axios from "axios";
import authHeader from "../helper/auth-header";

const API_URL = "http://localhost:8080/v1/admin/actor";

const getActors = (name, page, rowsPerPage) => {
  console.log("Get actors", name, " page ", page, " rows ", rowsPerPage);
  const PARAMS =
    "?" +
    (name ? "name=" + name + "&" : "") +
    (page ? "page=" + page + "&" : "") +
    (rowsPerPage ? "size=" + rowsPerPage + "&" : "");
  return fetch(API_URL + PARAMS);
};

const addActor = (actor) => {
  console.log("Add actor request", actor);
  return axios.post(API_URL, actor, { headers: authHeader() });
};

export default {
  addActor,
  getActors,
};
