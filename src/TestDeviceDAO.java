public class Main {

    public static void main(String[] args) {

        DeviceDAO deviceDAO = new DeviceDAO();

        System.out.println(deviceDAO.getAllDeviceWIthHearthRateAndGps("paolo"));
    }
}
