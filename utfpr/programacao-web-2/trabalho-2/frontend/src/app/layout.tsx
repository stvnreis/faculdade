import type { Metadata } from "next";
import { Inter } from "next/font/google";
import "./globals.css";
import { NextUiProvider } from "@/components/providers/next-ui-provider";
import { NextAuthSessionProvider } from "@/components/providers/session-provider";
import { Toaster } from "sonner";

const inter = Inter({ subsets: ["latin"] });

export const metadata: Metadata = {
  title: "Create Next App",
  description: "Generated by create next app",
};

export default function RootLayout({
  children,
}: Readonly<{
  children: React.ReactNode;
}>) {
  return (
    <html lang="en">
        <body className={inter.className + ' w-full h-screen'}>
          <NextUiProvider>
            {/* <NextAuthSessionProvider> */}
              <Toaster />
              {children}
            {/* </NextAuthSessionProvider> */}
          </NextUiProvider>
        </body>
    </html>
  );
}