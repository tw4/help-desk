import { TicketEndpoints } from "@/enums/APIEnum";
import axios from "axios";

export const getAllTickets = async (token: string) => {
  const res = axios.get(TicketEndpoints.GetAllTickets).then((res) => {
    if (res.data.success) {
      return res.data.data;
    } else {
      return [];
    }
  });
  return res;
};
