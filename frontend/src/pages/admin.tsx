import MainLayout from "@/components/layout/MainLayout";
import { Tabs, TabsList, TabsTrigger } from "@/components/ui/tabs";
import { Role } from "@/enums/Role";
import { useAuth } from "@/hook/Auth";
import { useAppSelector } from "@/hook/Redux";
import { User } from "@/types/userType";
import { TabsContent } from "@radix-ui/react-tabs";
import { useRouter } from "next/router";
import { useEffect } from "react";

const Admin = () => {
  // auth check
  useAuth();

  // router next
  const router = useRouter();

  // redux state
  const user: User = useAppSelector((state) => state.user.value as User);

  useEffect(() => {
    if (user?.role !== Role.ADMIN.toString()) {
      router.push("/dashboard");
    }
  }, [user]);

  return (
    <MainLayout>
      <Tabs defaultValue="stats">
        <TabsList>
          <TabsTrigger value="stats">Stats</TabsTrigger>
          <TabsTrigger value="config">Config</TabsTrigger>
        </TabsList>
        <TabsContent className="w-full" value="stats">
          Stats
        </TabsContent>
        <TabsContent className="w-full" value="config">
          Config
        </TabsContent>
      </Tabs>
    </MainLayout>
  );
};

export default Admin;
