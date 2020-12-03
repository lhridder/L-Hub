package nl.lucasridder.lhub.methods;

public class PlayerCount {

    public static int getGlobalPlayerCount() {
        return 0;
        /*
        try {
            Socket socket = new Socket();
            socket.connect(new InetSocketAddress("vps3.lucasridder.nl", 25565), 1000);

            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            DataInputStream in = new DataInputStream(socket.getInputStream());

            out.write(0xFE);

            StringBuilder str = new StringBuilder();

            int b;
            while((b = in.read()) != -1) {
                if(b > 16 && b != 255 && b != 23 && b != 24) {
                    str.append((char) b);
                }
            }

            String[] data = str.toString().split("§");
            return Integer.parseInt(data[1]);
        } catch(Exception e) {
            e.printStackTrace();
        }
        return -1;
        */
    }

}
