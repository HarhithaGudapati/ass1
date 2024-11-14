package sports.acs560.performance_analyzer;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import sports.acs560.performance_analyzer.Application.Analysis;
import sports.acs560.performance_analyzer.Application.Team;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class ApplicationTest {

    private List<Team> sampleTeams;

    @BeforeEach
    void setUp() {
        sampleTeams = new ArrayList<>();
        sampleTeams.add(new Team(2023, "Chelsea", "Premier League", 15, 10, 45));
        sampleTeams.add(new Team(2023, "Liverpool", "Premier League", 18, 7, 50));
        sampleTeams.add(new Team(2023, "Manchester City", "Premier League", 20, 5, 65));
        sampleTeams.add(new Team(2023, "Manchester Utd", "Premier League", 10, 15, 30));
    }

    @Test
    void testCalculateMeanPoints() {
        Analysis analysis = Application.analyzeData(sampleTeams);
        assertEquals(47.5, analysis.getMeanPoints(), 0.001, "Mean points should be 47.5");
    }

    @Test
    void testCalculateMedianPoints() {
        Analysis analysis = Application.analyzeData(sampleTeams);
        assertEquals(47.5, analysis.getMedianPoints(), 0.001, "Median points should be 47.5");
    }

    @Test
    void testFindMostWinsTeam() {
        Analysis analysis = Application.analyzeData(sampleTeams);
        assertNotNull(analysis.getMostWinsTeam(), "Most Wins Team should not be null");
        assertEquals("Manchester City", analysis.getMostWinsTeam().getName(), "Most Wins Team should be Manchester City");
    }

    @Test
    void testFindMostLossesTeam() {
        Analysis analysis = Application.analyzeData(sampleTeams);
        assertNotNull(analysis.getMostLossesTeam(), "Most Losses Team should not be null");
        assertEquals("Manchester Utd", analysis.getMostLossesTeam().getName(), "Most Losses Team should be Manchester Utd");
    }

    @Test
    void testFindHighestPointsTeam() {
        Analysis analysis = Application.analyzeData(sampleTeams);
        assertNotNull(analysis.getHighestPointsTeam(), "Highest Points Team should not be null");
        assertEquals("Manchester City", analysis.getHighestPointsTeam().getName(), "Highest Points Team should be Manchester City");
    }

    @Test
    void testFindLowestPointsTeam() {
        Analysis analysis = Application.analyzeData(sampleTeams);
        assertNotNull(analysis.getLowestPointsTeam(), "Lowest Points Team should not be null");
        assertEquals("Manchester Utd", analysis.getLowestPointsTeam().getName(), "Lowest Points Team should be Manchester Utd");
    }

    @Test
    void testReadDataFromCSV() {
        // Assuming readDataFromCSV is properly handling the CSV format, 
        // here we could simulate it or directly check for a valid list size.
        List<Team> teams = Application.readDataFromCSV("sports_data.csv");
        assertFalse(teams.isEmpty(), "Data read from CSV should not be empty");
    }

    @Test
    void testCreateReport() {
        Analysis analysis = Application.analyzeData(sampleTeams);
        Application.createReport(analysis, "test_report.txt");
        File reportFile = new File("test_report.txt");
        assertTrue(reportFile.exists(), "Report file should be created");
        assertTrue(reportFile.length() > 0, "Report file should not be empty");

        // Clean up test report file after testing
        reportFile.delete();
    }
}


