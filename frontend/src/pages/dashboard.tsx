import React, { use, useEffect, useState } from "react";
import { useAuth } from "@/hook/Auth";
import axios from "axios";
import { AuthEndpoints, UserEndpoints } from "@/enums/APIEnum";

const Dashboard = () => {
  useAuth();

  return <main>dashboard page</main>;
};

export default Dashboard;
