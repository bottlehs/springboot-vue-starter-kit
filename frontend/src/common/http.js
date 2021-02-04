import axios from "axios";
import { API_URL } from "./config";
import responseInterceptors from "./http.interceptors.response";
const http = axios.create({
  baseURL: API_URL
});

http.interceptors.request.use(
  config => {
    return config;
  },
  error => {
    Promise.reject(error);
  }
);

http.interceptors.response.use(undefined, responseInterceptors(http));

export default http;
