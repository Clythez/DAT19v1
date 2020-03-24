public class Country {

    String countryName;
    String year;
    String totalPopulation;

    public Country() {
    }

    public Country(String countryName, String year, String totalPopulation) {
        this.countryName = countryName;
        this.year = year;
        this.totalPopulation = totalPopulation;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getTotalPopulation() {
        return totalPopulation;
    }

    public void setTotalPopulation(String totalPopulation) {
        this.totalPopulation = totalPopulation;
    }
}
