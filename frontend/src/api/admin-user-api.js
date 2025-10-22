import axios from "axios";
import authHeader from "../helper/auth-header";

const API_URL = "http://localhost:8080/v1/admin/user";

const getUsers = (username, page, rowsPerPage) => {
  const PARAMS =
    "?" +
    (username ? "username=" + username + "&" : "") +
    (page ? "page=" + page + "&" : "") +
    (rowsPerPage ? "size=" + rowsPerPage + "&" : "");
  return fetch(API_URL + PARAMS);
};

const getUser = (username) => {
  return fetch(API_URL + `/${username}`);
};

const banOrUnbanUser = (username, banned) => {
  return axios.patch(
    API_URL + "/ban",
    { username: username, banOrUnban: !banned },
    { headers: authHeader() }
  );
};

export default {
  getUsers,
  getUser,
  banOrUnbanUser,
};
