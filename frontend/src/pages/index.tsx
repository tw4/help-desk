/* eslint-disable @next/next/no-img-element */
import { Button } from "@/components/ui/button";
import { Form, FormField, FormMessage } from "@/components/ui/form";
import { Input } from "@/components/ui/input";
import { Label } from "@/components/ui/label";
import { useForm } from "react-hook-form";
import { zodResolver } from "@hookform/resolvers/zod";
import * as z from "zod";
import axios from "axios";
import { AuthEndpoints } from "@/enums/APIEnum";
import { useState } from "react";
import { set } from "date-fns";

const formSchema = z.object({
  email: z.string().email().min(2, "Email must be at least 6 characters"),
  password: z.string().min(6, "Password must be at least 6 characters"),
});

const Home = () => {
  const [error, setError] = useState<string>("");

  const form = useForm<z.infer<typeof formSchema>>({
    resolver: zodResolver(formSchema),
    defaultValues: {
      email: "",
      password: "",
    },
  });

  const onSubmit = (data: z.infer<typeof formSchema>) => {
    axios.post(AuthEndpoints.Login, data).then((res) => {
      if (res.data.success) {
        localStorage.setItem("token", res.data.data);
        setError("");
      } else {
        setError(res.data.message);
      }
    });
  };

  return (
    <main className="max-w[1440px] h-[100vh]  flex flex-row items-center justify-center">
      <div>
        <h1 className="text-4xl font-bold text-center">Login</h1>
        <p className="text-center">Welcome back</p>
        <Form {...form}>
          <form
            onSubmit={form.handleSubmit(onSubmit)}
            className="space-y-1 min-w-[300px]">
            <FormField
              name="email"
              control={form.control}
              render={({ field }) => (
                <Label>
                  Email
                  <Input {...field} />
                </Label>
              )}
            />
            <FormMessage>{form.formState.errors.email?.message}</FormMessage>
            <FormField
              name="password"
              control={form.control}
              render={({ field }) => (
                <Label>
                  Password
                  <Input type="password" {...field} />
                </Label>
              )}
            />
            <FormMessage>{form.formState.errors.password?.message}</FormMessage>
            <Button type="submit">Submit</Button>
            <FormMessage>{error}</FormMessage>
          </form>
        </Form>
      </div>
    </main>
  );
};

export default Home;
