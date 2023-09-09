package com.poincenot.saint.btrader.rest.controller;

import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

public class TarjetaCredito {

    private String marca;
    private String numeroTarjeta;
    private String cardholder;
    private Date fechaVencimiento;

    public TarjetaCredito(String marca, String numeroTarjeta, String cardholder, Date fechaVencimiento) {
        this.marca = marca;
        this.numeroTarjeta = numeroTarjeta;
        this.cardholder = cardholder;
        this.fechaVencimiento = fechaVencimiento;
    }

    public String getMarca() {
        return marca;
    }

    public String getNumeroTarjeta() {
        return numeroTarjeta;
    }

    public String getCardholder() {
        return cardholder;
    }

    public Date getFechaVencimiento() {
        return fechaVencimiento;
    }

    public boolean esValida() {
        return getImporte() < 1000;
    }

    public boolean esValidaParaOperar() {
        return getFechaVencimiento().after(new Date());
    }
    
    public double getImporte() {
        return Double.parseDouble(numeroTarjeta.substring(12, 16));
    }
    public boolean esDistintaA(TarjetaCredito otraTarjeta) {
        return !this.numeroTarjeta.equals(otraTarjeta.numeroTarjeta);
    }

    public double getTasaOperacion(String marca, double importe, int mes) throws Exception {
        if (marca.equals("VISA")) {
            return importe * (Calendar.getInstance().get(Calendar.YEAR) / mes);
        } else if (marca.equals("NARA")) {
            return importe * (mes / 2);
        } else if (marca.equals("AMEX")) {
            return importe * (mes / 100);
        } else {
            throw new Exception("Marca de tarjeta no válida");
        }
    }

    public static void main(String[] args) {
    	TarjetaCredito tarjeta = null;
    	double importeTotal = 0;
    	
        Scanner scanner = new Scanner(System.in);

        System.out.println("Ingrese la marca de la tarjeta:");
        String marca = scanner.next();

        if (!marca.equalsIgnoreCase("AMEX") && !marca.equalsIgnoreCase("VISA") && !marca.equalsIgnoreCase("NARA")) {
            System.out.println("La marca de la tarjeta es invalida.");
            return;
        }

        System.out.println("Ingrese el número de la tarjeta:");
        String numeroTarjeta = scanner.next();

        if (numeroTarjeta.length() != 16) {
            System.out.println("El número de la tarjeta debe tener 16 caracteres.");
            return;
        }

        if (!numeroTarjeta.matches("^[0-9]{16}$")) {
            System.out.println("El número de la tarjeta debe ser una cadena de caracteres numéricos de 16 caracteres.");
            return;
        }

        System.out.println("Ingrese el nombre y apellido del titular:");
        String cardholder = scanner.next();

        if (cardholder.length() < 1) {
            System.out.println("El nombre y apellido del titular debe tener al menos 1 carácter.");
            return;
        }

        System.out.println("Ingrese la fecha de vencimiento (dd/MM/yyyy):");
        String fechaVencimientoStr = scanner.next();

        try {
            Date fechaVencimiento = new Date(fechaVencimientoStr);
            tarjeta = new TarjetaCredito(marca, numeroTarjeta, cardholder, fechaVencimiento);
        } catch (IllegalArgumentException e) {
            System.out.println("La fecha de vencimiento no es válida.");
            return;
        }

        System.out.println("¿Qué desea hacer?");
        System.out.println("1. Verificar si el monto es válido");
        System.out.println("2. Verificar si la tarjeta puede operar hoy");
        System.out.println("3. Obtener la tasa de operación");
        System.out.println("4. Salir");

        int opcion = scanner.nextInt();

        switch (opcion) {
            case 1:
                System.out.println("Ingrese el importe de la operación:");
                double importe = scanner.nextDouble();
                importeTotal = importe;
                if(importe>=1000) {
                	System.out.println("El importe de la operación es valido");
                }else {
                	System.out.println("El importe de la operación no es valido.");
                }
                break;
            case 2:
                System.out.println("La tarjeta puede operar hoy: " + tarjeta.esValidaParaOperar());
                break;
            case 3:
                try {
                    System.out.println("La tasa de operación es: " + tarjeta.getTasaOperacion(tarjeta.getMarca(), importeTotal, Calendar.getInstance().get(Calendar.MONTH)));
                } catch (Exception e) {
                    System.out.println("Se ha producido un error: " + e.getMessage());
                }
                break;
            case 4:
                System.out.println("Hasta luego!");
                break;
        }
     }
}
