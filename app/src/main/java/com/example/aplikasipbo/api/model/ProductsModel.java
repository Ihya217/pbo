package com.example.aplikasipbo.api.model;

public class ProductsModel {
        private int id;
        private String judul;
        private  String isi;

        public ProductsModel(int idnya, String judul, String isi) {
                this.judul = judul;
                this.isi = isi;
        }

        public int getId() {
                return id;
        }

        public void setId(int id) {
                this.id = id;
        }

        public String getJudul() {
                return judul;
        }

        public void setJudul(String judul) {
                this.judul = judul;
        }

        public String getIsi() {
                return isi;
        }

        public void setIsi(String isi) {
                this.isi = isi;
        }
}

