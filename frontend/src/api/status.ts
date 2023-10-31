import { TicketStatusEndpoints } from "@/enums/APIEnum";
import axios from "axios";

export const addStatus = async (token: string, status: string) => {
  const res = await axios
    .post(
      TicketStatusEndpoints.AddTicketStatus,
      {
        status: status,
      },
      {
        headers: {
          token: localStorage.getItem("token"),
        },
      }
    )
    .then((res) => {
      if (res.data.success) {
        return res.data;
      } else {
        return res.data;
      }
    });
  return res;
};
