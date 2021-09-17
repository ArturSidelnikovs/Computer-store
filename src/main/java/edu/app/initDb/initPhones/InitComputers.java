package edu.app.initDb.initPhones;

import edu.app.model.phone.Computer;
import edu.app.model.phone.OperatingSystem;
import edu.app.model.phone.ScreenTechnology;
import edu.app.service.IService;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class InitComputers {
    private final IService<Computer> service;

    public InitComputers(IService<Computer> service) {
        this.service = service;
    }

    public void initPhones() {
        List<Computer> list = new ArrayList<>();
        Computer computer1 = new Computer();
        computer1.setName("Ноутбук Apple MacBook Air 13 2020 MWTJ2");
        computer1.setImg("https://www.iguides.ru/upload/medialibrary/f1e/f1ee0da4d4d5e10c3ad34eaf80960e72.jpg");
        computer1.setImages(Arrays.asList("https://pocketnow.com/files/2020/11/MacBook-Air.jpg"));
        computer1.setOs(OperatingSystem.MACOS);
        computer1.setScreenSize(13.3);
        computer1.setProcessor("Intel Core i3 1000G4");
        computer1.setRandomAccessMemory(8);
        computer1.setNumberOfMatrixPoints(60);
        computer1.setAccumulatorCapacity(4990);
        computer1.setSsdCapacity(256);
        computer1.setPrice(2858);
        computer1.setNumberOfMainCameras(1);
        computer1.setCpuClockSpeed(2840);
        computer1.setGraphicsAccelerator("built-in video card");
        computer1.setBackCoverMaterial("aluminum");
        computer1.setBodyColor("gold");
        computer1.setBodyMaterial("aluminum");
        computer1.setAccumulatorType("None");
        computer1.setConnectionConnector("USB Type-C");
        computer1.setLength(304.1);
        computer1.setScreenRefreshRate(120);
        computer1.setScreenTechnology(ScreenTechnology.IPS);
        computer1.setThickness(212.4);
        computer1.setWeight(16);
        computer1.setWidth(1290);
        computer1.setWifi("5.0");
        list.add(computer1);

        Computer computer2 = new Computer();
        computer2.setName("Ноутбук Apple MacBook Pro 16 2019 MVVJ2");
        computer2.setImg("https://content2.onliner.by/catalog/device/main@2/e2a641bf8c80242a4362e8594b1c7758.jpeg");
        computer2.setImages(Arrays.asList("https://content2.onliner.by/catalog/device/main@2/db499f52fa0d4b9c31fd30231359b4c1.jpeg"));
        computer2.setOs(OperatingSystem.MACOS);
        computer2.setScreenSize(16.0);
        computer2.setProcessor("Intel Core i7 9750H");
        computer2.setRandomAccessMemory(16);
        computer2.setNumberOfMatrixPoints(60);
        computer2.setAccumulatorCapacity(10000);
        computer2.setSsdCapacity(256);
        computer2.setPrice(6140);
        computer2.setNumberOfMainCameras(1);
        computer2.setCpuClockSpeed(4500 );
        computer2.setGraphicsAccelerator("built-in video card");
        computer2.setBackCoverMaterial("aluminum");
        computer2.setBodyColor("grey");
        computer2.setBodyMaterial("aluminum");
        computer2.setAccumulatorType("Li-ion");
        computer2.setConnectionConnector("USB Type-C");
        computer2.setLength(357.9);
        computer2.setScreenRefreshRate(60);
        computer2.setScreenTechnology(ScreenTechnology.IPS);
        computer2.setThickness(16.2);
        computer2.setWeight(2000);
        computer2.setWidth(245.9);
        computer2.setWifi("5.0");

        list.add(computer2);

        Computer computer3 = new Computer();
        computer3.setName("Ноутбук Huawei MateBook 13, AMD Ryzen 7 3700U");
        computer3.setImg("https://avatars.mds.yandex.net/get-mpic/1582909/img_id1113645355513166747.jpeg/orig");
        computer3.setImages(Arrays.asList("https://static.1k.by/images/products/ip/big/pp2/6/4233757/i896c3c58a.jpg"));
        computer3.setOs(OperatingSystem.WINDOWS);
        computer3.setScreenSize(13);
        computer3.setProcessor("AMD Ryzen 7 3700U");
        computer3.setRandomAccessMemory(8);
        computer3.setNumberOfMatrixPoints(60);
        computer3.setAccumulatorCapacity(5740);
        computer3.setSsdCapacity(256);
        computer3.setPrice(3500);
        computer3.setNumberOfMainCameras(1);
        computer3.setCpuClockSpeed(3400);
        computer3.setGraphicsAccelerator("Intel UHD Graphics 620");
        computer3.setBackCoverMaterial("алюминий");
        computer3.setBodyColor("grey");
        computer3.setBodyMaterial("алюминий");
        computer3.setAccumulatorType("None");
        computer3.setConnectionConnector("USB Type-C");
        computer3.setLength(304);
        computer3.setScreenRefreshRate(90);
        computer3.setScreenTechnology(ScreenTechnology.IPS);
        computer3.setThickness(14.6);
        computer3.setWeight(1330);
        computer3.setWidth(217);
        computer3.setWifi("5.0");

        list.add(computer3);

        Computer computer4 = new Computer();
        computer4.setName("Ноутбук Lenovo Yoga Slim 7i 14IIL05 Dark Moss");
        computer4.setImg("https://shop.lenovo.by/storage/app/uploads/public/600/b0f/ce2/600b0fce21c19811252338.jpg");
        computer4.setImages(Arrays.asList("https://shop.lenovo.by/storage/app/uploads/public/600/b0f/cd8/600b0fcd87df4428688162.jpg"));
        computer4.setOs(OperatingSystem.WINDOWS);
        computer4.setScreenSize(15.6);
        computer4.setProcessor("Intel Core i5-1035G4 Processor ");
        computer4.setRandomAccessMemory(8);
        computer4.setNumberOfMatrixPoints(48);
        computer4.setAccumulatorCapacity(6700);
        computer4.setSsdCapacity(256);
        computer4.setPrice(350);
        computer4.setNumberOfMainCameras(1);
        computer4.setCpuClockSpeed(2960);
        computer4.setGraphicsAccelerator("Intel Iris Plus Graphics");
        computer4.setBackCoverMaterial("алюминий");
        computer4.setBodyColor("темно-серый");
        computer4.setBodyMaterial("алюминий");
//        computer4.setAccumulatorType("None");
        computer4.setConnectionConnector("USB Type-C");
        computer4.setLength(320.6);
        computer4.setScreenRefreshRate(300);
        computer4.setScreenTechnology(ScreenTechnology.IPS);
        computer4.setThickness(15.1);
        computer4.setWeight(1500);
        computer4.setWidth(208.18);
        computer4.setWifi("5.0");

        list.add(computer4);

        Computer computer5 = new Computer();
        computer5.setName("Моноблок Apple iMac M1 2021 24 MGPC3");
        computer5.setImg("https://www.iport.ru/upload/files/product/imac_24_2p_silver_0.jpg");
        computer5.setImages(Arrays.asList("https://www.ixbt.com/img/r30/00/02/41/39/IMG7099.jpg"));
        computer5.setOs(OperatingSystem.MACOS);
        computer5.setScreenSize(23.5);
        computer5.setProcessor("Apple M1");
        computer5.setRandomAccessMemory(8);
        computer5.setNumberOfMatrixPoints(60);
        computer5.setAccumulatorCapacity(4200);
        computer5.setSsdCapacity(256);
        computer5.setPrice(5030);
        computer5.setNumberOfMainCameras(1);
        computer5.setCpuClockSpeed(6270);
        computer5.setGraphicsAccelerator("встроенная");
        computer5.setBackCoverMaterial("алюминий");
        computer5.setBodyColor("серебристый");
        computer5.setBodyMaterial("алюминий");
//        computer5.setAccumulatorType("None");
        computer5.setConnectionConnector("2 USB Type-C");
        computer5.setLength(547);
        computer5.setScreenRefreshRate(60);
        computer5.setScreenTechnology(ScreenTechnology.IPS);
        computer5.setThickness(147);
        computer5.setWeight(4480);
        computer5.setWidth(461);
        computer5.setWifi("5.0");

        list.add(computer5);

        Computer computer6 = new Computer();
        computer6.setName("Ноутбук Acer Aspire A515-55G-51VV");
        computer6.setImg("https://antenka.by/wp-content/uploads/2021/02/7fdbf777dbea982aa2718dc088908e8cf823533e.jpg");
        computer6.setImages(Arrays.asList("https://fk.by/uploads/images/cache/2021/01/28/noutbuk-acer-aspire-5-a515-55g-51vv-nx-hzheu-007_8-1100x500.jpeg"));
        computer6.setOs(OperatingSystem.WINDOWS);
        computer6.setScreenSize(15.6);
        computer6.setProcessor("Intel Core i5-1035G1");
        computer6.setRandomAccessMemory(4);
        computer6.setNumberOfMatrixPoints(60);
        computer6.setAccumulatorCapacity(5000);
        computer6.setSsdCapacity(256);
        computer6.setPrice(2311);
        computer6.setNumberOfMainCameras(3);
        computer6.setCpuClockSpeed(2840);
        computer6.setGraphicsAccelerator("Adreno 650");
        computer6.setBackCoverMaterial("металл");
        computer6.setBodyColor("серебристый");
        computer6.setBodyMaterial("металл");
//        computer6.setAccumulatorType("Li-ion");
        computer6.setConnectionConnector("USB Type-C");
        computer6.setLength(363.4);
        computer6.setScreenRefreshRate(144);
        computer6.setScreenTechnology(ScreenTechnology.IPS);
        computer6.setThickness(17.95);
        computer6.setWeight(1900);
        computer6.setWidth(250.5);
        computer6.setWifi("5.0");

        list.add(computer6);

        Computer computer7 = new Computer();
        computer7.setName("Компактный компьютер Apple Mac mini (MD387)");
        computer7.setImg("https://content2.onliner.by/catalog/device/main@2/ff4e0c11868ad6d0f6916c8ff2e10699.jpg");
        computer7.setImages(Arrays.asList("https://content2.onliner.by/catalog/device/main@2/3048b41c6bf5a05a10ef25028d2af224.jpg"));
        computer7.setOs(OperatingSystem.MACOS);
        computer7.setScreenSize(5.8);
        computer7.setProcessor("Intel Core i5");
        computer7.setRandomAccessMemory(4);
        computer7.setNumberOfMatrixPoints(12);
//        computer7.setAccumulatorCapacity(3100);
        computer7.setSsdCapacity(512);
        computer7.setPrice(1100);
//        computer7.setNumberOfMainCameras(1);
        computer7.setCpuClockSpeed(2200);
        computer7.setGraphicsAccelerator("Intel HD Graphics 4000");
        computer7.setBackCoverMaterial("металл");
        computer7.setBodyColor("серебристый");
        computer7.setBodyMaterial("металл");
//        computer7.setAccumulatorType("Li-ion");
        computer7.setConnectionConnector("USB Type-C");
        computer7.setLength(197);
//        computer7.setScreenRefreshRate(60);
        computer7.setScreenTechnology(ScreenTechnology.OLED);
        computer7.setThickness(36);
        computer7.setWeight(1220);
        computer7.setWidth(197);
        computer7.setWifi("5.0");

        list.add(computer7);

        Computer computer8 = new Computer();
        computer8.setName("Ноутбук Lenovo IdeaPad 5 15ITL05");
        computer8.setImg("https://shop.lenovo.by/storage/app/uploads/public/60d/222/779/60d222779fe4c483692901.jpg");
        computer8.setImages(Arrays.asList("https://shop.lenovo.by/storage/app/uploads/public/608/95b/809/60895b80957e0844507948.jpg"));
        computer8.setOs(OperatingSystem.WINDOWS);
        computer8.setScreenSize(15.6);
        computer8.setProcessor("Intel Core i5-1135G7");
        computer8.setRandomAccessMemory(8);
        computer8.setNumberOfMatrixPoints(60);
        computer8.setAccumulatorCapacity(7000);
        computer8.setSsdCapacity(1000);
        computer8.setPrice(1341);
        computer8.setNumberOfMainCameras(1);
        computer8.setCpuClockSpeed(2900);
        computer8.setGraphicsAccelerator("NVIDIA GeForce MX450");
        computer8.setBackCoverMaterial("алюминий");
        computer8.setBodyColor("серый");
        computer8.setBodyMaterial("металл");
//        computer8.setAccumulatorType("Li-ion");
        computer7.setConnectionConnector("USB Type-C");
        computer8.setLength(356.67);
        computer8.setScreenRefreshRate(60);
        computer8.setScreenTechnology(ScreenTechnology.OLED);
        computer8.setThickness(16.9);
        computer8.setWeight(1660);
        computer8.setWidth(233.13);
        computer8.setWifi("5.0");

        list.add(computer8);

        savePhones(list);

    }

    private void savePhones(List<Computer> computers) {
        computers.forEach(service::save);
    }
}

