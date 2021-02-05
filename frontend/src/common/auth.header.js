import Jwt from "./jwt";

export default function authHeader() {
  let accessToken = Jwt.getAccessToken();

  if (accessToken) {
    return {
      "X-AUTH-TOKEN": accessToken
    };
  } else {
    return {};
  }
}
