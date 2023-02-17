import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DeviceDAO {

    public List<Device> getAllDeviceWIthHearthRateAndGps(String username){

        String url = "jdbc:mysql://localhost:3306/mydb2";

        String user = "root";

        String password = "qwerty12345678910#";

        List<Device> deviceList = new ArrayList<>();

        try {

            Connection connection = DriverManager.getConnection(url, user, password);

            String sql = "select * from device where DEVICE_HAS_GPS <> 0 and DEVICE_HAS_HEARTRATE <> 0 and username = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1,username);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){

                Device device = new Device(
                        resultSet.getString("DEVICE_PART_NUMBER"),
                        resultSet.getString("DEVICE_MANUFACTURER"),
                        resultSet.getString("DEVICE_MODEL"),
                        resultSet.getShort("DEVICE_HAS_GPS"),
                        resultSet.getShort("DEVICE_HAS_HEARTRATE"),
                        resultSet.getString("USERNAME"));
                deviceList.add(device);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return deviceList;
    }
}
