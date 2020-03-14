import java.util.*;
import java.io.*;
import java.math.*;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
class Solution {
    
    static final int PARSE_BEGIN_INDEX = 1;
    static final int SKIP_CHARS = 3;
    
    static Line[] lines;
    int W;
    int H;
    int rowCount;

    public static void main(String args[]) {
        Solution solution = new Solution();
        solution.parse();
        solution.play();
    }
    
    void parse() {
        //Parsing header
        Scanner in = new Scanner(System.in);
        this.W = in.nextInt();
        this.H = in.nextInt();
        this.rowCount = H - 2;
        in.nextLine();
        
        //Parsing labels
        String[] names = in.nextLine().split("  ");
        lines = new Line[names.length];
        
        for(int i = 0; i < names.length; i++) {
            Line line = new Line(names[i], rowCount);
            lines[i] = line;
        }

        //Parsing lines
        for (int i = 0; i < rowCount; i++) {
            String line = in.nextLine();
            int index = PARSE_BEGIN_INDEX;
            int lineNumber = 0;
            while (index < W) {
                if(line.charAt(index) == '-') {
                    lines[lineNumber].setConnector(i, lines[lineNumber+1]);
                    lines[lineNumber+1].setConnector(i, lines[lineNumber]);
                }
                index += SKIP_CHARS;
                lineNumber++;
            }
        }
        
        //Parsing label results
        String[] results = in.nextLine().split("  ");
        for(int i = 0; i < results.length; i++) {
            lines[i].setResult(results[i]);
        }
    }
    
    void play() {
        for(int i = 0; i < lines.length; i++) {
            Line startLine = lines[i];
            Line resultLine = getResultLine(startLine, 0);
            System.out.println(startLine.getName() + resultLine.getResult());
        }
    }
    
    Line getResultLine(Line currentLine, int row) {
        if (row >= rowCount) {
            return currentLine;
        }
        
        if(currentLine.hasResultLineByRow(row)){
            return currentLine.getResultLineByRow(row);
        }
        
        Line nextLine = currentLine;
        if(currentLine.hasConnector(row)) {
            nextLine = currentLine.getConnector(row);
        }
        
        Line resultLine = getResultLine(nextLine, row + 1);
        currentLine.setResultLineByRow(row, resultLine);
        return resultLine;
    }
    
    class Line {
        String name;
        String result;
        
        Line[] connectors;
        Line[] resultLineByRow;
        
        Line(String name, int rowCount){
            this.name = name;
            this.connectors = new Line[rowCount];
            this.resultLineByRow = new Line[rowCount];
        }
        
        String getName() {
            return name;
        }
        
        void setResult(String result){
            this.result = result;
        }
        
        void setResultLineByRow(int rowNumber, Line result) {
            this.resultLineByRow[rowNumber] = result;
        }
        
        String getResult() {
            return result;
        }
        
        void setConnector(int rowNumber, Line connector){
            this.connectors[rowNumber] = connector;
        }
        
        boolean hasConnector(int rowNumber) {
            return getConnector(rowNumber) != null;
        }
        
        Line getConnector(int rowNumber) {
            return connectors[rowNumber];
        }
        
        boolean hasResultLineByRow(int rowNumber) {
            return getResultLineByRow(rowNumber) != null;
        }
        
        Line getResultLineByRow(int rowNumber) {
            return resultLineByRow[rowNumber];
        }
    } 
}
