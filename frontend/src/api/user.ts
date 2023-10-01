import { UserEndpoints } from "@/enums/APIEnum";
import axios from "axios";

export const getUser = (token: string) => {
  const res = axios
    .get(UserEndpoints.GetMe, {
      headers: {
        token: token,
      },
    })
    .then((res) => {
      if (res.data.success) {
        return res.data.data;
      } else {
        return null;
      }
    });
  return res;
};
