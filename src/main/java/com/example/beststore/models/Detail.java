package com.example.beststore.models;

import jakarta.persistence.*;






    @Entity
    @Table(name = "detail") // Tên bảng trong cơ sở dữ liệu
   public class Detail {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private long id;
        private String name;
        private double price;

        private String imageFilename;
        private String Sales;
        private double percent;
        private String title;
        private String description;

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }



        public String getImageFilename() {
            return imageFilename;
        }

        public void setImageFilename(String imageFilename) {
            this.imageFilename = imageFilename;
        }

        public String getSales() {
            return Sales;
        }

        public void setSales(String sales) {
            Sales = sales;
        }

        public double getPercent() {
            return percent;
        }

        public void setPercent(double percent) {
            this.percent = percent;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }
