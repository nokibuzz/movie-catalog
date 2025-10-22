import axios from "axios";

const API_URL = "http://localhost:8080/v1/image/";

const uploadImage = (image) => {
  return axios.post(API_URL + "upload", image);
};

export default { uploadImage };
