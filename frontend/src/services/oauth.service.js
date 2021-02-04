import http from "@/common/http";
import authHeader from "@/common/auth.header";
const SERVICE_API_URL = "/api";

class OauthService {
  login(params = {}) {
    let form = new FormData();
    form.append('email', params.email);
    form.append('password',params.password)

    return http.post(SERVICE_API_URL + "/login", form, {
      headers: authHeader()
    });
  }

  logout(params = {}) {
    return http.get(SERVICE_API_URL + "/logout", {
      params: params,
      headers: authHeader()
    });
  }

  register(params = {}) {
    return http.get(SERVICE_API_URL + "/register", {
      params: params,
      headers: authHeader()
    });
  }
}

export default new OauthService();
