import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class MovieInfo {

    String color;
    String directorName;
    String numOfCritics;
    String duration;
    String dirFBLikes;
    String act3FBLikes;
    String act2Name;
    String act1FBLikes;
    String grossIncome;
    String genres;
    String actorName;
    String movieName;
    String totalVotes;
    String totalFBLikesCast;
    String actor3Name;
    String numberFacesPoster;
    String plotKeywords;
    String imdbLink;
    String numberUserReviews;
    String language;
    String country;
    String contentRating;
    String budget;
    String year;
    String act2FBLikes;
    String imdbScore;
    String aspectRatio;
    String movieFBLikes;

    public MovieInfo() {
    }

    public MovieInfo(String color, String directorName, String numOfCritics, String duration, String dirFBLikes, String act3FBLikes, String act2Name, String act1FBLikes, String grossIncome, String genres, String actorName, String movieName, String totalVotes, String totalFBLikesCast, String actor3Name, String numberFacesPoster, String plotKeywords, String imdbLink, String numberUserReviews, String language, String country, String contentRating, String budget, String year, String act2FBLikes, String imdbScore, String aspectRatio, String movieFBLikes) {
        this.color = color;
        this.directorName = directorName;
        this.numOfCritics = numOfCritics;
        this.duration = duration;
        this.dirFBLikes = dirFBLikes;
        this.act3FBLikes = act3FBLikes;
        this.act2Name = act2Name;
        this.act1FBLikes = act1FBLikes;
        this.grossIncome = grossIncome;
        this.genres = genres;
        this.actorName = actorName;
        this.movieName = movieName;
        this.totalVotes = totalVotes;
        this.totalFBLikesCast = totalFBLikesCast;
        this.actor3Name = actor3Name;
        this.numberFacesPoster = numberFacesPoster;
        this.plotKeywords = plotKeywords;
        this.imdbLink = imdbLink;
        this.numberUserReviews = numberUserReviews;
        this.language = language;
        this.country = country;
        this.contentRating = contentRating;
        this.budget = budget;
        this.year = year;
        this.act2FBLikes = act2FBLikes;
        this.imdbScore = imdbScore;
        this.aspectRatio = aspectRatio;
        this.movieFBLikes = movieFBLikes;
    }

    public static Comparator<MovieInfo> movieInfoComparatorYear = new Comparator<MovieInfo>() {

        public int compare(MovieInfo info1, MovieInfo info2) {
            String Year1 = info1.getYear();
            String Year2 = info2.getYear();

            //ascending order
            // return Year1.compareTo(Year2);

            //descending order
            return Year2.compareTo(Year1);
        }};

    public static Comparator<MovieInfo> movieInfoComparatorAscendingRating = new Comparator<MovieInfo>() {

        public int compare(MovieInfo info1, MovieInfo info2) {
            String rating1 = info1.getImdbScore();
            String rating2 = info2.getImdbScore();

            //ascending order
            return rating1.compareTo(rating2);

            //descending order
            // return Year2.compareTo(Year1);
        }};

    public static Comparator<MovieInfo> movieInfoComparatorDescendingRating = new Comparator<MovieInfo>() {

        public int compare(MovieInfo info1, MovieInfo info2) {
            String rating1 = info1.getImdbScore();
            String rating2 = info2.getImdbScore();

            // ascending order
            // return rating1.compareTo(rating2);

            // descending order
            return rating2.compareTo(rating1);
        }};

    public void printFour() {
        ReadFromCSV filler = new ReadFromCSV();
        ArrayList<MovieInfo> print4 = filler.readFromCSV();
        Menu menu = new Menu();

        Collections.sort(print4, MovieInfo.movieInfoComparatorYear);

        String dir = "Director";
        String act = "Lead Actor";
        String year1 = "Year";
        String mov = "Movie Name";

        System.out.printf("%-40s %-40s %-40s %-40s\n", dir, act, year1, mov);
        System.out.println();
        for (int i = 1; i < print4.size()-1; i++) { // i = 1; print4.size()

            String dirName = print4.get(i).getDirectorName();
            String actName = print4.get(i).getActorName();
            String year = print4.get(i).getYear();
            String movName = print4.get(i).getMovieName();

            System.out.printf("%-40s %-40s %-40s %-40s\n", dirName, actName, year, movName);

        }
        // menu.mainMain();
    }

    public void searchForActor(String actorName) {
        ReadFromCSV filler = new ReadFromCSV();
        ArrayList<MovieInfo> searchActor = filler.readFromCSV();
        Menu menu = new Menu();

        for (int i = 1; i < searchActor.size()-1; i++) {
            if (!(actorName.equalsIgnoreCase(searchActor.get(i).getActorName()))) {
                System.out.println("Sorry i found no match, please try again:");
                menu.searchForActor();
            }
            if (searchActor.get(i).getActorName().equalsIgnoreCase(actorName)) {
                System.out.println(actorName + ": Has appeared in: ");
                System.out.println("|-------------------------------------------------|");
                for (int j = 1; j < searchActor.size(); j++) {
                    if (actorName.equalsIgnoreCase(searchActor.get(j).getActorName())) {
                        System.out.println("Movie Name: " + searchActor.get(j).getMovieName());
                        System.out.println("Year: " + searchActor.get(j).getYear());
                        System.out.println("Duration: " + searchActor.get(j).getDuration());
                        System.out.println();
                    }
                }
                System.out.println("|-------------------------------------------------|");
                break;
            }
        }
    }

    public void searchForDirector(String directorName) {
        ReadFromCSV filler = new ReadFromCSV();
        ArrayList<MovieInfo> searchDirector = filler.readFromCSV();
        Menu menu = new Menu();

        for (int i = 1; i < searchDirector.size()-1; i++) {
            if (!(directorName.equalsIgnoreCase(searchDirector.get(i).getDirectorName()))) {
                System.out.println("Sorry i found no match, please try again:");
                menu.searchForDirector();
            }
            if (searchDirector.get(i).getDirectorName().equalsIgnoreCase(directorName)) {
                System.out.println(directorName + ": Has appeared in: ");
                System.out.println("|-------------------------------------------------|");
                for (int j = 1; j < searchDirector.size(); j++) {
                    if (actorName.equalsIgnoreCase(searchDirector.get(j).getDirectorName())) {
                        System.out.println("Movie Name: " + searchDirector.get(j).getMovieName());
                        System.out.println("Year: " + searchDirector.get(j).getYear());
                        System.out.println("Duration: " + searchDirector.get(j).getDuration());
                        System.out.println();
                    }
                }
                System.out.println("|-------------------------------------------------|");
                break;
            }
        }
    }

    public void ratingsDesc() {
        ReadFromCSV filler = new ReadFromCSV();
        ArrayList<MovieInfo> ratingsDesc = filler.readFromCSV();
        Menu menu = new Menu();

        Collections.sort(ratingsDesc, MovieInfo.movieInfoComparatorDescendingRating);

        String mov = "Movie Name";
        String imdb = "IMDB Rating";

        System.out.printf("%-60s %-60s\n", mov, imdb);
        System.out.println();
        for (int i = 1; i < ratingsDesc.size()-1; i++) {

            String movieName = ratingsDesc.get(i).getMovieName();
            String imdbScore = ratingsDesc.get(i).getImdbScore();

            System.out.printf("%-60s %-60s\n", movieName, imdbScore);

        }
        // menu.mainMain();
    }

    public void ratingsAsc() {
        ReadFromCSV filler = new ReadFromCSV();
        ArrayList<MovieInfo> ratingsAsc = filler.readFromCSV();
        Menu menu = new Menu();

        Collections.sort(ratingsAsc, MovieInfo.movieInfoComparatorAscendingRating);

        String mov = "Movie Name";
        String imdb = "IMDB Rating";

        System.out.printf("%-60s %-60s\n", mov, imdb);
        System.out.println();
        for (int i = 1; i < ratingsAsc.size()-1; i++) {

            String movieName = ratingsAsc.get(i).getMovieName();
            String imdbScore = ratingsAsc.get(i).getImdbScore();

            System.out.printf("%-60s %-60s\n", movieName, imdbScore);

        }
        // menu.mainMain();
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getDirectorName() {
        return directorName;
    }

    public void setDirectorName(String directorName) {
        this.directorName = directorName;
    }

    public String getNumOfCritics() {
        return numOfCritics;
    }

    public void setNumOfCritics(String numOfCritics) {
        this.numOfCritics = numOfCritics;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getDirFBLikes() {
        return dirFBLikes;
    }

    public void setDirFBLikes(String dirFBLikes) {
        this.dirFBLikes = dirFBLikes;
    }

    public String getAct3FBLikes() {
        return act3FBLikes;
    }

    public void setAct3FBLikes(String act3FBLikes) {
        this.act3FBLikes = act3FBLikes;
    }

    public String getAct2Name() {
        return act2Name;
    }

    public void setAct2Name(String act2Name) {
        this.act2Name = act2Name;
    }

    public String getAct1FBLikes() {
        return act1FBLikes;
    }

    public void setAct1FBLikes(String act1FBLikes) {
        this.act1FBLikes = act1FBLikes;
    }

    public String getGrossIncome() {
        return grossIncome;
    }

    public void setGrossIncome(String grossIncome) {
        this.grossIncome = grossIncome;
    }

    public String getGenres() {
        return genres;
    }

    public void setGenres(String genres) {
        this.genres = genres;
    }

    public String getActorName() {
        return actorName;
    }

    public void setActorName(String actorName) {
        this.actorName = actorName;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public String getTotalVotes() {
        return totalVotes;
    }

    public void setTotalVotes(String totalVotes) {
        this.totalVotes = totalVotes;
    }

    public String getTotalFBLikesCast() {
        return totalFBLikesCast;
    }

    public void setTotalFBLikesCast(String totalFBLikesCast) {
        this.totalFBLikesCast = totalFBLikesCast;
    }

    public String getActor3Name() {
        return actor3Name;
    }

    public void setActor3Name(String actor3Name) {
        this.actor3Name = actor3Name;
    }

    public String getNumberFacesPoster() {
        return numberFacesPoster;
    }

    public void setNumberFacesPoster(String numberFacesPoster) {
        this.numberFacesPoster = numberFacesPoster;
    }

    public String getPlotKeywords() {
        return plotKeywords;
    }

    public void setPlotKeywords(String plotKeywords) {
        this.plotKeywords = plotKeywords;
    }

    public String getImdbLink() {
        return imdbLink;
    }

    public void setImdbLink(String imdbLink) {
        this.imdbLink = imdbLink;
    }

    public String getNumberUserReviews() {
        return numberUserReviews;
    }

    public void setNumberUserReviews(String numberUserReviews) {
        this.numberUserReviews = numberUserReviews;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getContentRating() {
        return contentRating;
    }

    public void setContentRating(String contentRating) {
        this.contentRating = contentRating;
    }

    public String getBudget() {
        return budget;
    }

    public void setBudget(String budget) {
        this.budget = budget;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getAct2FBLikes() {
        return act2FBLikes;
    }

    public void setAct2FBLikes(String act2FBLikes) {
        this.act2FBLikes = act2FBLikes;
    }

    public String getImdbScore() {
        return imdbScore;
    }

    public void setImdbScore(String imdbScore) {
        this.imdbScore = imdbScore;
    }

    public String getAspectRatio() {
        return aspectRatio;
    }

    public void setAspectRatio(String aspectRatio) {
        this.aspectRatio = aspectRatio;
    }

    public String getMovieFBLikes() {
        return movieFBLikes;
    }

    public void setMovieFBLikes(String movieFBLikes) {
        this.movieFBLikes = movieFBLikes;
    }

    @Override
    public String toString() {
        return this.directorName + " " + this.actorName + " " + this.movieName + " " + this.imdbScore;
    }

}

/*
if (print4.get(i).getDirectorName().length() <= 5) {
                System.out.print(print4.get(i).getDirectorName() + " \t\t\t\t\t ");
                System.out.print(print4.get(i).getActorName());

                if (print4.get(i).getActorName().length() <= 5 ) {
                    System.out.print(" \t\t\t " + print4.get(i).getYear());
                    System.out.println(" \t\t\t\t " + print4.get(i).getMovieName());
                } else if (print4.get(i).getActorName().length() > 5 &&  print4.get(i).getActorName().length() <= 10) {
                    System.out.print(" \t\t\t " + print4.get(i).getYear());
                    System.out.println(" \t\t\t\t " + print4.get(i).getMovieName());
                } else if (print4.get(i).getActorName().length() > 10 &&  print4.get(i).getActorName().length() <= 15) {
                    System.out.print(" \t\t\t " + print4.get(i).getYear());
                    System.out.println(" \t\t\t\t " + print4.get(i).getMovieName());
                } else if (print4.get(i).getActorName().length() > 15 &&  print4.get(i).getActorName().length() <= 18) {
                    System.out.print(" \t\t " + print4.get(i).getYear());
                    System.out.println(" \t\t\t\t " + print4.get(i).getMovieName());
                } else if (print4.get(i).getActorName().length() > 18) {
                    System.out.print(" \t\t " + print4.get(i).getYear());
                    System.out.println(" \t\t\t\t " + print4.get(i).getMovieName());
                }

            } else if (print4.get(i).getDirectorName().length() > 5 && print4.get(i).getDirectorName().length() <= 10) {
                System.out.print(print4.get(i).getDirectorName() + " \t\t\t\t ");
                System.out.print(print4.get(i).getActorName());
                if (print4.get(i).getActorName().length() <= 5 ) {
                    System.out.print(" \t\t\t\t\t\t " + print4.get(i).getYear());
                    System.out.println(" \t\t\t\t " + print4.get(i).getMovieName());
                } else if (print4.get(i).getActorName().length() > 5 &&  print4.get(i).getActorName().length() <= 10) {
                    System.out.print(" \t\t\t\t\t " + print4.get(i).getYear());
                    System.out.println(" \t\t\t\t " + print4.get(i).getMovieName());
                } else if (print4.get(i).getActorName().length() > 10 &&  print4.get(i).getActorName().length() <= 15) {
                    System.out.print(" \t\t\t\t " + print4.get(i).getYear());
                    System.out.println(" \t\t\t\t " + print4.get(i).getMovieName());
                } else if (print4.get(i).getActorName().length() > 15 &&  print4.get(i).getActorName().length() <= 18) {
                    System.out.print(" \t\t\t " + print4.get(i).getYear());
                    System.out.println(" \t\t\t\t " + print4.get(i).getMovieName());
                } else if (print4.get(i).getActorName().length() > 18) {
                    System.out.print(" \t\t " + print4.get(i).getYear());
                    System.out.println(" \t\t\t\t " + print4.get(i).getMovieName());
                }

            } else if (print4.get(i).getDirectorName().length() > 10 && print4.get(i).getDirectorName().length() <= 15) {
                System.out.print(print4.get(i).getDirectorName() + " \t\t\t ");
                System.out.print(print4.get(i).getActorName());
                if (print4.get(i).getActorName().length() <= 5 ) {
                    System.out.print(" \t\t\t\t\t\t " + print4.get(i).getYear());
                    System.out.println(" \t\t\t\t " + print4.get(i).getMovieName());
                } else if (print4.get(i).getActorName().length() > 5 &&  print4.get(i).getActorName().length() <= 10) {
                    System.out.print(" \t\t\t\t\t " + print4.get(i).getYear());
                    System.out.println(" \t\t\t\t " + print4.get(i).getMovieName());
                } else if (print4.get(i).getActorName().length() > 10 &&  print4.get(i).getActorName().length() <= 15) {
                    System.out.print(" \t\t\t\t " + print4.get(i).getYear());
                    System.out.println(" \t\t\t\t " + print4.get(i).getMovieName());
                } else if (print4.get(i).getActorName().length() > 15 &&  print4.get(i).getActorName().length() <= 18) {
                    System.out.print(" \t\t\t " + print4.get(i).getYear());
                    System.out.println(" \t\t\t\t " + print4.get(i).getMovieName());
                } else if (print4.get(i).getActorName().length() > 18) {
                    System.out.print(" \t\t " + print4.get(i).getYear());
                    System.out.println(" \t\t\t\t " + print4.get(i).getMovieName());
                }


            } else if (print4.get(i).getDirectorName().length() > 15 && print4.get(i).getDirectorName().length() <= 18) {
                System.out.print(print4.get(i).getDirectorName() + " \t\t ");
                System.out.print(print4.get(i).getActorName());
                if (print4.get(i).getActorName().length() <= 5 ) {
                    System.out.print(" \t\t\t\t\t\t " + print4.get(i).getYear());
                    System.out.println(" \t\t\t\t " + print4.get(i).getMovieName());
                } else if (print4.get(i).getActorName().length() > 5 &&  print4.get(i).getActorName().length() <= 10) {
                    System.out.print(" \t\t\t\t\t " + print4.get(i).getYear());
                    System.out.println(" \t\t\t\t " + print4.get(i).getMovieName());
                } else if (print4.get(i).getActorName().length() > 10 &&  print4.get(i).getActorName().length() <= 15) {
                    System.out.print(" \t\t\t\t " + print4.get(i).getYear());
                    System.out.println(" \t\t\t\t " + print4.get(i).getMovieName());
                } else if (print4.get(i).getActorName().length() > 15 &&  print4.get(i).getActorName().length() <= 18) {
                    System.out.print(" \t\t\t " + print4.get(i).getYear());
                    System.out.println(" \t\t\t\t " + print4.get(i).getMovieName());
                } else if (print4.get(i).getActorName().length() > 18) {
                    System.out.print(" \t\t " + print4.get(i).getYear());
                    System.out.println(" \t\t\t\t " + print4.get(i).getMovieName());
                }


            } else if (print4.get(i).getDirectorName().length() > 18) {
                System.out.print(print4.get(i).getDirectorName() + " \t " );
                System.out.print(print4.get(i).getActorName());
                if (print4.get(i).getActorName().length() <= 5 ) {
                    System.out.print(" \t\t\t\t\t\t " + print4.get(i).getYear());
                    System.out.println(" \t\t\t\t " + print4.get(i).getMovieName());
                } else if (print4.get(i).getActorName().length() > 5 &&  print4.get(i).getActorName().length() <= 10) {
                    System.out.print(" \t\t\t\t\t " + print4.get(i).getYear());
                    System.out.println(" \t\t\t\t " + print4.get(i).getMovieName());
                } else if (print4.get(i).getActorName().length() > 10 &&  print4.get(i).getActorName().length() <= 15) {
                    System.out.print(" \t\t\t\t " + print4.get(i).getYear());
                    System.out.println(" \t\t\t\t " + print4.get(i).getMovieName());
                } else if (print4.get(i).getActorName().length() > 15 &&  print4.get(i).getActorName().length() <= 18) {
                    System.out.print(" \t\t\t " + print4.get(i).getYear());
                    System.out.println(" \t\t\t\t " + print4.get(i).getMovieName());
                } else if (print4.get(i).getActorName().length() > 18) {
                    System.out.print(" \t\t " + print4.get(i).getYear());
                    System.out.println(" \t\t\t\t " + print4.get(i).getMovieName());
                }

            }
        }
 */