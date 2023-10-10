import MainLayout from "@/components/layout/MainLayout";
import { useRouter } from "next/router";

const TicketDetail = () => {
  const router = useRouter();
  return <MainLayout>{router.query.id}</MainLayout>;
};

export default TicketDetail;
