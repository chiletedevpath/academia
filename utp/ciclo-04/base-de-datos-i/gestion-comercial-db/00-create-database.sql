/********************************************************************************************
    SISTEMA DE BASE DE DATOS: GESTIÓN COMERCIAL

    AUTOR: ADRIAN PISCO SOTO
********************************************************************************************/


/********************************************************************************************
    0. CREACIÓN DE LA BASE DE DATOS
********************************************************************************************/
IF DB_ID('GestionComercialDB') IS NULL
BEGIN
    CREATE DATABASE GestionComercialDB;
END;
GO

USE GestionComercialDB;
GO
