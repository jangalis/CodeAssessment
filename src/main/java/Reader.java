import com.jangalis.Models.Airport;
import com.jangalis.Models.Country;
import com.jangalis.Models.Runway;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class Reader {
    private static final String TABLE_HEADER_COUNTRY_AIRPORTS = "<tr><th>Country</th><th>ID</th><th>Name</th><th>Ref</th><th>Surface</th><th>Ident</th></tr>";
    private static final String TABLE_HEADER_COUNTRIES = "<tr><th>Country</th><th>Airports quantity</th></th></tr>";
    private static final String TABLE_HEADER_SURFACES = "<tr><th>Country</th><th>ASP</th><th>TURF</th><th>CONC</th><th>CON</th><th>GRS</th><th>GRE</th><th>ASPH</th><th>TURF-G</th><th>UNK</th><th>OTHER</th></tr>";
    private static final String TABLE_DESCRIPTION_HIGH = "10 countries with highest number of airports";
    private static final String TABLE_DESCRIPTION_LOW = "10 countries with lowest number of airports";
    private static final String TABLE_DESCRIPTION_SURFACES = "Runway surfaces";

    /*
    Change path to files after pull.
     */
    private static final String AIRPORTS_CSV_PATH = "C:\\Users\\GC\\Desktop\\CSVtoWEB\\src\\main\\resources\\airports.csv";
    private static final String COUNTRIES_CSV_PATH = "C:\\Users\\GC\\Desktop\\CSVtoWEB\\src\\main\\resources\\countries.csv";
    private static final String RUNWAYS_CSV_PATH = "C:\\Users\\GC\\Desktop\\CSVtoWEB\\src\\main\\resources\\runways.csv";

    private static ArrayList<Airport> airports;
    private static HashMap<String, Runway> runways;
    private static HashMap<String, Country> countries;

    public Reader() throws IOException {
        airports = readAirports();
        runways = readRunways();
        countries = readCountries();
        addDataToAirports();
    }

    private void addDataToAirports() {
        for (Airport airport : airports) {
            String id = airport.getId();
            String countryCode = airport.getCountryCode();

            if (runways.containsKey(id)) {
                airport.setAirportRef(runways.get(id).getAirportRef());
                airport.setRunwaySurface(runways.get(id).getSurface());
            } else {
                airport.setAirportRef("no info");
                airport.setRunwaySurface("no info");
            }

            if (countries.containsKey(countryCode)) {

                airport.setCountry(countries.get(countryCode).getName());

                Country country = countries.get(countryCode);
                country.setAmount(countries.get(countryCode).getAmount() + 1);

                String surface = airport.getRunwaySurface();

                switch (surface) {
                    case "ASP":
                        country.setAspQty(country.getAspQty() + 1);
                        break;
                    case "TURF":
                        country.setTurfQty(country.getTurfQty() + 1);
                        break;
                    case "CONC":
                        country.setConcQty(country.getConcQty() + 1);
                        break;
                    case "CON":
                        country.setConQty(country.getConQty() + 1);
                        break;
                    case "GRS":
                        country.setGrsQty(country.getGrsQty() + 1);
                        break;
                    case "GRE":
                        country.setGreQty(country.getGreQty() + 1);
                        break;
                    case "ASPH":
                        country.setAsphQty(country.getAsphQty() + 1);
                        break;
                    case "TURF-G":
                        country.setTurfgQty(country.getTurfgQty() + 1);
                        break;
                    case "UNK":
                        country.setUnkQty(country.getUnkQty() + 1);
                        break;
                    default:
                        country.setOtherQty(country.getOtherQty() + 1);
                        break;
                }
            } else {
                airport.setCountry("no info");
            }
        }
    }

    private HashMap<String, Country> readCountries() {
        try {
            HashMap<String, Country> countries = new HashMap<>();
            BufferedReader bufferedReader = new BufferedReader(
                    new InputStreamReader(
                            new FileInputStream(COUNTRIES_CSV_PATH), "UTF-8"));

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                Country country = new Country();
                String[] str = line.split(",");
                country.setCode(str[1].replaceAll("\"", ""));
                country.setName(str[2].replaceAll("\"", ""));
                countries.put(str[1].replaceAll("\"", ""), country);
            }
            bufferedReader.close();
            return countries;
        } catch (Exception exception) {
            return null;
        }
    }

    private HashMap<String, Runway> readRunways() {
        try {
            HashMap<String, Runway> runways = new HashMap<>();
            BufferedReader bufferedReader = new BufferedReader(
                    new InputStreamReader(
                            new FileInputStream(RUNWAYS_CSV_PATH), "UTF-8"));

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                Runway runway = new Runway();
                String[] str = line.split(",");
                runway.setAirportRef(str[0].replaceAll("\"", ""));
                runway.setId(str[1].replaceAll("\"", ""));
                runway.setSurface(str[5].replaceAll("\"", ""));

                runways.put(runway.getId(), runway);
            }
            bufferedReader.close();
            return runways;
        } catch (Exception exception) {
            return null;
        }
    }


    private ArrayList<Airport> readAirports() {
        try {
            ArrayList<Airport> airports = new ArrayList<>();
            BufferedReader bufferedReader = new BufferedReader(
                    new InputStreamReader(
                            new FileInputStream(AIRPORTS_CSV_PATH), "UTF-8"));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                Airport airport = new Airport();
                String[] str = line.split(",");
                airport.setId(str[0].replaceAll("\"", ""));
                airport.setAirportIdent(str[1].replaceAll("\"", ""));
                airport.setName(str[3].replaceAll("\"", ""));
                airport.setCountryCode(str[8].replaceAll("\"", ""));
                airports.add(airport);
            }
            bufferedReader.close();
            return airports;
        } catch (Exception e) {
            return null;
        }
    }

    String getCountryAirports(String query) {

        String responseTable = "<table>" + TABLE_HEADER_COUNTRY_AIRPORTS;
        for (Airport air : airports) {
            String country = air.getCountry().toLowerCase();

            if (country.equals(query.toLowerCase())) {
                responseTable = responseTable + ("<tr>");
                responseTable = responseTable + (air.toString());
                responseTable = responseTable + ("</tr>");
            }
        }

        if (!responseTable.isEmpty()) {
            return responseTable + "</table>";
        }
        return null;
    }

    String getAirportsQuantityTables() {
        ArrayList<Country> country = new ArrayList<>(countries.values());
        Collections.sort(country);

        String responseTable = "<br><p>" + TABLE_DESCRIPTION_HIGH + "</p><table>" + TABLE_HEADER_COUNTRIES;
        for (int i = country.size() - 1; i > country.size() - 11; i--) {
            responseTable = responseTable + ("<tr>");
            responseTable = responseTable + (country.get(i).toString());
            responseTable = responseTable + ("</tr>");
        }

        responseTable = responseTable + "</table><br><p>" + TABLE_DESCRIPTION_LOW + "</p><table>" + TABLE_HEADER_COUNTRIES;
        for (int i = 0; i < 10; i++) {
            responseTable = responseTable + ("<tr>");
            responseTable = responseTable + (country.get(i).toString());
            responseTable = responseTable + ("</tr>");
        }
        return responseTable + "</table>";
    }

    String getSurfacesTable() {
        ArrayList<Country> country = new ArrayList<>(countries.values());

        String responseTable = "<br><p>" + TABLE_DESCRIPTION_SURFACES + "</p><table>" + TABLE_HEADER_SURFACES;
        for (Country c : country) {
            responseTable = responseTable + ("<tr>");
            responseTable = responseTable + (c.surfacesToString());
            responseTable = responseTable + ("</tr>");
        }
        return responseTable + "</table>";
    }
}
