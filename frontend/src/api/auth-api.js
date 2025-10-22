import axios from "axios";

const API_URL = "http://localhost:8080/v1/user/";

const register = (name, surname, username, email, password, avatarID) => {
  return axios.post(API_URL + "signup", {
    name,
    surname,
    username,
    email,
    password,
    avatarID,
  });
};

const login = (username, password) => {
  return axios
    .post(API_URL + "signin", {
      username,
      password,
    })
    .then((response) => {
      if (response.data.token) {
        localStorage.setItem("user", JSON.stringify(response.data));
      }

      return response.data;
    });
};

const logout = () => {
  localStorage.removeItem("user");
};

const getCurrentUser = () => {
  return JSON.parse(localStorage.getItem("user"));
};

export default {
  register,
  login,
  logout,
  getCurrentUser,
};
