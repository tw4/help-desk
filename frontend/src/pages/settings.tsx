import { getUser } from "@/api/user";
import MainLayout from "@/components/layout/MainLayout";
import { Button } from "@/components/ui/button";
import {
  Form,
  FormControl,
  FormField,
  FormItem,
  FormLabel,
  FormMessage,
} from "@/components/ui/form";
import { Input } from "@/components/ui/input";
import { Role } from "@/enums/Role";
import { useAuth } from "@/hook/Auth";
import { useAppDispatch, useAppSelector } from "@/hook/Redux";
import { addUser } from "@/store/features/user/userSlice";
import { User } from "@/types/userType";
import { zodResolver } from "@hookform/resolvers/zod";
import { useRouter } from "next/router";
import { useEffect } from "react";
import { useForm } from "react-hook-form";
import { TypeOf, z } from "zod";

// basic info form schema
const basicInfoFormSchema = z.object({
  name: z.string().min(2, "First name must be at least 2 characters"),
  surname: z.string().min(2, "Last name must be at least 2 characters"),
  email: z.string().email("Invalid email address"),
  role: z.string(),
});

// password form schema
const passwordFormSchema = z.object({
  password: z.string().min(6, "Password must be at least 6 characters"),
  confirmPassword: z.string().min(6, "Password must be at least 6 characters"),
});

const Settings = () => {
  // auth
  useAuth();

  // router
  const router = useRouter();

  // redux state
  let user: User = useAppSelector((state) => state.user.value as User);

  // redux dispatch
  const dispatch = useAppDispatch();

  // basic info form
  const basicInfoForm = useForm<z.infer<typeof basicInfoFormSchema>>({
    resolver: zodResolver(basicInfoFormSchema),
    defaultValues: {
      name: "",
      surname: "",
      email: "",
    },
  });

  useEffect(() => {
    if (!user) {
      getUser(localStorage.getItem("token") || "").then((res) => {
        if (res) {
          dispatch(addUser(res));
        } else {
          router.push("/");
        }
      });
    }
  }, []);

  // basit info form submit
  const basicInfoFormSubmit = (data: z.infer<typeof basicInfoFormSchema>) => {
    console.log(data);
  };

  return (
    <MainLayout>
      <h1 className="text-3xl font-bold mt-2">My Account</h1>
      {user && (
        <Form {...basicInfoForm}>
          <form
            onSubmit={basicInfoForm.handleSubmit(basicInfoFormSubmit)}
            className="border-2 border-r-gray-200 rounded-xl p-4 mt-4 space-y-3">
            <FormField
              control={basicInfoForm.control}
              name="name"
              render={({ field }) => (
                <FormItem>
                  <FormLabel>name</FormLabel>
                  <FormControl>
                    <Input placeholder={user.name} {...field} />
                  </FormControl>
                  <FormMessage />
                </FormItem>
              )}
            />
            <FormField
              control={basicInfoForm.control}
              name="surname"
              render={({ field }) => (
                <FormItem>
                  <FormLabel>surname</FormLabel>
                  <FormControl>
                    <Input placeholder={user.surname} {...field} />
                  </FormControl>
                  <FormMessage />
                </FormItem>
              )}
            />
            <FormField
              control={basicInfoForm.control}
              name="email"
              render={({ field }) => (
                <FormItem>
                  <FormLabel>email</FormLabel>
                  <FormControl>
                    <Input placeholder={user.email} {...field} />
                  </FormControl>
                  <FormMessage />
                </FormItem>
              )}
            />
            <Button className="mt-4">Update</Button>
          </form>
        </Form>
      )}
    </MainLayout>
  );
};

export default Settings;
