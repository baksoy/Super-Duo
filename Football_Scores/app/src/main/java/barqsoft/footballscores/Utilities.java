package barqsoft.footballscores;

/**
 * Created by yehya khaled on 3/3/2015.
 */
public class Utilities {
    public static final int CHAMPIONS_LEAGUE = 405;
    public static final int BUNDESLIGA1 = 394;
    public static final int BUNDESLIGA2 = 395;
    public static final int LIGUE1 = 396;
    public static final int LIGUE2 = 397;
    public static final int PREMIER_LEAGUE = 398;
    public static final int PRIMERA_DIVISION = 399;
    public static final int SEGUNDA_DIVISION = 400;
    public static final int SERIE_A = 401;
    public static final int PRIMEIRA_LIGA = 402;
    public static final int BUNDESLIGA3 = 403;
    public static final int EREDIVISIE = 404;

    public static String getLeague(int league_num) {
        switch (league_num) {
            case SERIE_A:
                return "Seria A";
            case PREMIER_LEAGUE:
                return "Premier League";
            case CHAMPIONS_LEAGUE:
                return "UEFA Champions League";
            case PRIMERA_DIVISION:
                return "Primera Division";
            case BUNDESLIGA1:
                return "Bundesliga 1";
            case BUNDESLIGA2:
                return "Bundesliga 2";
            case BUNDESLIGA3:
                return "Bundesliga 3";
            case LIGUE1:
                return "Ligue 1";
            case LIGUE2:
                return "Ligue 2";
            case SEGUNDA_DIVISION:
                return "Segunda Division";
            case PRIMEIRA_LIGA:
                return "Primeira Liga";
            case EREDIVISIE:
                return "Eredivisie";
            default:
                return "Unknown League. Please report.";
        }
    }

    public static String getMatchDay(int match_day, int league_num) {
        if (league_num == CHAMPIONS_LEAGUE) {
            if (match_day <= 6) {
                return "Group Stages, Matchday : 6";
            } else if (match_day == 7 || match_day == 8) {
                return "First Knockout round";
            } else if (match_day == 9 || match_day == 10) {
                return "QuarterFinal";
            } else if (match_day == 11 || match_day == 12) {
                return "SemiFinal";
            } else {
                return "Final";
            }
        } else {
            return "Matchday : " + String.valueOf(match_day);
        }
    }

    public static String getScores(int home_goals, int awaygoals) {
        if (home_goals < 0 || awaygoals < 0) {
            return " - ";
        } else {
            return String.valueOf(home_goals) + " - " + String.valueOf(awaygoals);
        }
    }

    public static int getTeamCrestByTeamName(String teamname) {
        if (teamname == null) {
            return R.drawable.no_icon;
        }
        switch (teamname) { //This is the set of icons that are currently in the app. Feel free to find and add more
            //as you go.
            case "Liverpool FC":
                return R.drawable.liverpool;
            case "Club Atlético de Madrid":
                return R.mipmap.atletico_madrid_logo;
            case "Málaga CF":
                return R.mipmap.malaga_cf_logo;
            case "RC Celta de Vigo":
                return R.mipmap.rc_celta_de_vigo_logo;
            case "FC Barcelona":
                return R.mipmap.fc_barcelona;
            case "Rayo Vallecano de Madrid":
                return R.mipmap.rayo_vallecano_logo;
            case "Levante UD":
                return R.mipmap.ic_launcherlevante_ud_logo;
            case "Real Betis":
                return R.mipmap.real_betis;
            case "Getafe CF":
                return R.mipmap.getafe_cf_logo;
            case "RC Deportivo La Coruna":
                return R.mipmap.rc_deportivo_la_coruna_logo;
            case "RCD Espanyol":
                return R.mipmap.espanyol_logo;
            case "Sevilla FC":
                return R.mipmap.sevilla_fc_logo;
            case "Sporting Gijón":
                return R.mipmap.sporting_gijon_logo;
            case "Real Madrid CF":
                return R.mipmap.real_madrid_logo;
            case "Real Sociedad de Fútbol":
                return R.mipmap.real_sociedad_san_sebastian;
            case "SD Eibar":
                return R.mipmap.sd_eibar_logo;
            case "Arsenal FC":
                return R.drawable.arsenal;
            case "Crystal Palace FC":
                return R.drawable.crystal_palace_fc;
            case "Swansea City FC":
                return R.drawable.swansea_city_afc;
            case "Aston Villa FC":
                return R.drawable.aston_villa;
            case "Manchester City FC":
                return R.drawable.manchester_city;
            case "West Bromwich Albion FC":
                return R.drawable.west_bromwich_albion_hd_logo;
            case "Newcastle United FC":
                return R.drawable.newcastle_united;
            case "Southampton FC":
                return R.drawable.southampton_fc;
            case "Chelsea FC":
                return R.drawable.chelsea;
            case "Manchester United FC":
                return R.drawable.manchester_united;
            case "Swansea City":
                return R.drawable.swansea_city_afc;
            case "Leicester City FC":
                return R.drawable.leicester_city_fc_hd_logo;
            case "Everton FC":
                return R.drawable.everton_fc_logo1;
            case "West Ham United FC":
                return R.drawable.west_ham;
            case "Tottenham Hotspur FC":
                return R.drawable.tottenham_hotspur;
            case "West Bromwich Albion":
                return R.drawable.west_bromwich_albion_hd_logo;
            case "Sunderland AFC":
                return R.drawable.sunderland;
            case "Stoke City FC":
                return R.drawable.stoke_city;
            default:
                return R.drawable.no_icon;
        }
    }
}
