import http from "@/common/http";
import authHeader from "@/common/auth.header";
const SERVICE_API_URL = "/api/user";

class UserService {
  search(params = {}) {
    return http.get(SERVICE_API_URL + "/search", {
      params: params,
      headers: authHeader()
    });
  }
  getAll(params = {}) {
    return http.get(SERVICE_API_URL, {
      params: params,
      headers: authHeader()
    });
  }
  getId(id) {
    return http.get(SERVICE_API_URL + "/" + id, {
      headers: authHeader()
    });
  }
  modify(id, params = {}) {
    return http.put(SERVICE_API_URL + "/" + id, params, {
      headers: authHeader()
    });
  }
  remove(id) {
    return http.delete(SERVICE_API_URL + "/" + id, {
      headers: authHeader()
    });
  }
  removeAll() {
    return http.delete(SERVICE_API_URL, {
      headers: authHeader()
    });
  }
  add(params) {
    return http.post(SERVICE_API_URL, params, {
      headers: authHeader()
    });
  }
}

export default new UserService();
