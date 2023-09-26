import { useEffect, useState } from "react";
import axios from "axios";
import { AuthEndpoints } from "@/enums/APIEnum";

export const useAuth = () => {
  // State
  const [isAuth, setIsAuth] = useState(false);

  // Check if user is authenticated
  useEffect(() => {
    axios
      .get(AuthEndpoints.VerifyToken, {
        headers: {
          "Content-Type": "application/json",
          token: localStorage.getItem("token") || "",
        },
      })
      .then((res) => {
        setIsAuth(res.data.success);
      });
  }, []);

  // Return isAuth state
  return { isAuth };
};
