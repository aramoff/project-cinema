package cinema;
import org.w3c.dom.ls.LSOutput;

import java.util.Scanner;
public class Cinema {

    public static void menu(){
        System.out.println("1. Show the seats");
        System.out.println("2. Buy a ticket");
        System.out.println("3. Statistics");
        System.out.println("0. Exit");
    }
    public static void cargarCine(String[][] cine,int rows, int seats){

        for(int o=0;o<rows;o++){
            for(int j=0;j<seats;j++){
                cine[o][j] = "S ";
            }
        }
    }
    public static void mostrarCinema(String[][] cinema01, int rows,int seats){
        System.out.println("Cinema:");
        System.out.print("  ");
        for(int i=0;i<seats;i++){
            System.out.print((i+1)+" ");
        }
        System.out.println();
        for(int o=0;o<rows;o++){
            System.out.print((o+1)+" ");
            for(int j=0;j<seats;j++){
                System.out.print(cinema01[o][j]);
            }
            System.out.println("");
        }
    }
    public static int comprarTicketMenu(int rows, int row){
        int precio;
        int rows_precio = 0;
        if(rows%2 == 0){
            rows_precio = rows/2+1;
        }else{
            rows_precio =rows/2;
        }
        if(row <= rows_precio){
            precio = 10;
        }else{
            precio= 8;
        }
        if((rows == 7) && (row == 4)){
            precio = 10;
        }
        return precio;
    }
    public static void cargarTicket(String[][] cinema,int row, int seat,int rows, int seats){
        if((row <=rows) && (seat <= seats)){
            cinema[row - 1][seat - 1] = "B ";
        }
    }

    public static boolean verificacionEleccion(int fila, int asiento,int filas,int asientos){
        if(fila <= (filas) && asiento <= (asientos) && fila >= 1 && asiento >=1){
            return true;
        }else{
            return false;

        }
    }
    public static void estadistica(int filas,int asientos,int tickets_comprados,int ingresos){
        int asientos_totales = filas * asientos;
        int tickets;
        float porcentaje = (float)tickets_comprados/(float)asientos_totales *100;
        if ((filas * asientos) < 60){
            tickets = 10 * filas * asientos;
        }else{
            if(filas%2 == 0){
                tickets = 10 * (filas/2) * asientos + 8 * (filas/2) * asientos;
            }else{
                tickets = 10 * (filas/2) * asientos + 8 * ((filas/2)+1) * asientos;
            }
        }
        System.out.println("Number of purchased tickets: "+tickets_comprados);
        System.out.printf("Percentage: %.2f%%",porcentaje);
        System.out.println("");
        System.out.println("Current income: $"+ingresos);
        System.out.println("Total income: $"+tickets);
        System.out.println("");
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the number of rows:");
        int rows = sc.nextInt();
        System.out.println("Enter the number of seats in each row:");
        int seats = sc.nextInt();
        System.out.println("");
        String[][] cinema = new String[rows][seats];
        cargarCine(cinema, rows, seats);
        int ticket_vendidos = 0;
        int precio;
        int ingresos_totales=0;
        int eleccion = 4;
        boolean input = true;
        while (eleccion != 0) {
            menu();
            eleccion = sc.nextInt();
            System.out.println("");
            switch (eleccion) {
                case 1:
                    System.out.println("");
                    mostrarCinema(cinema,rows,seats);
                    System.out.println("");
                    break;
                case 2:
                    input = true;
                    while(input) {
                        System.out.println("Enter a row number:");
                        int elec_row = sc.nextInt();
                        System.out.println("Enter a seat number in that row:");
                        int elec_seat = sc.nextInt();
                        if(verificacionEleccion(elec_row,elec_seat, rows,seats)){
                            if(cinema[elec_row-1][elec_seat-1] == "S "){
                                precio = comprarTicketMenu(rows,elec_row);
                                System.out.println("");
                                System.out.println("Ticket price: $"+precio);
                                ingresos_totales= ingresos_totales + precio;
                                cargarTicket(cinema,elec_row,elec_seat,rows,seats);
                                ticket_vendidos += 1;
                                input = false;
                            }else{
                                System.out.println("That ticket has already been purchased!");
                                System.out.println("");
                            }
                        }else{
                            System.out.println("Wrong input!");
                        }
                    }
                    break;
                case 3:
                    estadistica(rows,seats,ticket_vendidos,ingresos_totales);
                    break;
                case 0:
                    break;
            }
        }
    }
}