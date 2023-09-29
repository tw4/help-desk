import React, { use, useEffect, useState } from "react";
import { useAuth } from "@/hook/Auth";

const Dashboard = () => {
  useAuth();

  return <main>dashboard page</main>;
};

export default Dashboard;
