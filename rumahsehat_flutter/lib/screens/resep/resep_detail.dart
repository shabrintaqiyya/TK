class DetailResep {
  String id;
  String namaDokter;
  String namaPasien;
  String isDone;
  String namaApoteker;
  List<String> listJumlah;

  DetailResep(
    {required this.id,
      required this.namaDokter,
      required this.namaPasien,
      required this.isDone,
      required this.namaApoteker,
      required this.listJumlah
    }
  );

  factory DetailResep.fromJson(Map<String, dynamic> json) {
    return DetailResep(
      id: json['id'],
      namaDokter: json['nama_dokter'],
      namaPasien: json['nama_pasien'],
      isDone: json['is_done'],
      namaApoteker: json['nama_apoteker'],
      listJumlah: json['list_jumlah'],
    );
  }
}
