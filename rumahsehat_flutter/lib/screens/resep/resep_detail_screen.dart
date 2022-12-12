import 'package:flutter/material.dart';
import 'package:rumahsehat_flutter/screens/resep/resep_detail.dart';

class DetailResepPage extends StatelessWidget {
  final DetailResep detailResep;
  // final DetailAppointment detailAppointment;

  // DetailResepPage({required this.detailResep, required this.detailAppointment});
  DetailResepPage({required this.detailResep});

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text('Detail Resep'),
        leading: BackButton(
          onPressed: () => Navigator.pop(context),
        ),
      ),

      body: Center(
        /** Card Widget **/
        child: Card(
          elevation: 50,
          shadowColor: Colors.blue[100],
          color: Colors.white,
          child: SizedBox(
            width: 400,
            height: 300,
            child: Padding(
              padding: const EdgeInsets.all(20.0),
              child: Column(
                children: [
                  Text(
                    'Detail Resep',
                    style: TextStyle(
                      fontSize: 30,
                      fontWeight: FontWeight.w700,
                    ), //Textstyle
                  ), //Text
                  const SizedBox(
                    height: 30,
                  ),
                  Text(
                    'ID Resep : ${detailResep.id}',
                    style: TextStyle(
                      fontSize: 15,
                    ), //Textstyle
                  ), //Text
                  const SizedBox(
                    height: 10,
                  ),
                  Text(
                    'Nama Dokter : ${detailResep.namaDokter}',
                    style: TextStyle(
                      fontSize: 15,
                    ), //Textstyle
                  ), //Text
                  const SizedBox(
                    height: 30,
                  ),
                  Text(
                    'Nama Pasien : ${detailResep.namaPasien}',
                    style: TextStyle(
                      fontSize: 15,
                    ), //Textstyle
                  ),
                  const SizedBox(
                    height: 10,
                  ),
                  Text(
                    'Status : ${detailResep.isDone}',
                    style: TextStyle(
                      fontSize: 15,
                    ), //Textstyle
                  ), //SizedBox
                  // if (detailResep.isDone) ...[
                  //   const Text("Status : Sudah selesai",
                  //       style: TextStyle(fontSize: 15, fontFamily: "Verdana")),
                  // ] else ...[
                  //   const Text("Status : Belum selesai",
                  //       style: TextStyle(fontSize: 15, fontFamily: "Verdana")),
                  // ],
                  const SizedBox(
                    height: 10,
                  ), //SizedBox
                  if (detailResep.isDone == "Sudah selesai") ...[
                    Text('Nama Apoteker : ${detailResep.namaApoteker}',
                        style: TextStyle(fontSize: 15)),
                  ] else ...[
                    Text('Nama Apoteker : -',
                        style: TextStyle(fontSize: 15)),
                  ], //Text
                  const SizedBox(
                    height: 10,
                  ),
                  Text('List Obat : ', style: TextStyle(fontSize: 15)),
                  ListView(
                    children: [
                      for (var item in detailResep.listJumlah) Text(item),
                    ],
                  )
                ],
              ), //Column
            ), //Padding
          ), //SizedBox
        ), //Card
      ), //Center
    );
  }
}
