import 'dart:async';
import 'dart:convert';

import 'package:flutter/material.dart';
import 'package:http/http.dart' as http;
import 'package:rumahsehat_flutter/dropdown_dokter.dart';
import 'package:rumahsehat_flutter/screens/login/login_screen.dart';
import 'package:rumahsehat_flutter/screens/token.dart';

import '../../date_format.dart';
import '../../dialog.dart';

class CreateAppointmentPage extends StatefulWidget {
  const CreateAppointmentPage({Key? key}) : super(key: key);

  @override
  _CreateAppointmentState createState() => _CreateAppointmentState();
}

class _CreateAppointmentState extends State<CreateAppointmentPage> {
  DateTime datetime = DateTime.now();
  String dokter = "";

  late Future<List<Dokter>> listDokter;

  @override
  void initState() {
    super.initState();
    listDokter = fetchDokter(context);
  }

  setDokter(value) {
    setState(() {
      dokter = value;
    });
  }

  setDateTime(value) {
    setState(() {
      datetime = value;
    });
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text('Create Appointment'),
      ),
      body: Padding(
        padding: const EdgeInsets.all(16.0),
        child: Column(
          mainAxisSize: MainAxisSize.max,
          mainAxisAlignment: MainAxisAlignment.center,
          children: <Widget>[
            DateTimeFormat(callback: setDateTime),
            DropdownDokter(
              callback: setDokter,
              listDokter: listDokter,
            ),
            ElevatedButton(
                child: const Text('Create'),
                onPressed: () async {
                  bool success =
                      await createAppointment(datetime, dokter, context);

                  if (success) {
                    showDialog(
                        context: context,
                        builder: (BuildContext dialogContext) {
                          return const AlertDialogNew(
                              title: 'Success',
                              content: 'Create Appointment berhasil!');
                        });
                  } else {
                    showDialog(
                        context: context,
                        builder: (BuildContext dialogContext) {
                          return const AlertDialogNew(
                              title: 'Failed',
                              content: 'Create Appointment gagal!');
                        });
                  }
                })
          ],
        ),
      ),
    );
  }
}

Future<bool> createAppointment(
    DateTime dateTime, String dokterId, BuildContext context) async {
  String date = dateTime.toString().split(" ")[0];
  String time = dateTime.toString().split(" ")[1].split(".")[0];
  String waktuAwal = "${date}T$time";
  // Token
  String token = await getToken();
  String id = await getId();
  final response = await http.post(
    Uri.parse("http://localhost:8080/api/v1/appointment/add"),
    headers: <String, String>{
      'Content-Type': 'application/json; charset=UTF-8',
      'Authorization': token
    },
    body: jsonEncode(<String, dynamic>{
      'waktuAwal': waktuAwal,
      'dokterId': dokterId,
      'isDone': 0,
      'kode': 'APT-0',
      'pasienId': id
    }),
  );
  if (response.statusCode == 200) {
    return true;
  } else if (response.statusCode == 401) {
    Navigator.pushReplacement(
        context, MaterialPageRoute(builder: (_) => LoginScreen()));
    return false;
  } else {
    return false;
  }
}

Future<List<Dokter>> fetchDokter(BuildContext context) async {
  String token = await getToken();
  final response = await http.get(
      Uri.parse('http://localhost:8080/api/v1/list-dokter'),
      headers: <String, String>{'Authorization': token});

  if (response.statusCode == 200) {
    Iterable l = json.decode(response.body);
    List<Dokter> listDokter =
        List<Dokter>.from(l.map((model) => Dokter.fromJson(model)));
    return listDokter;
  } else if (response.statusCode == 401) {
    Navigator.pushReplacement(
        context, MaterialPageRoute(builder: (_) => LoginScreen()));
    throw Exception('Failed to send request');
  } else {
    throw Exception('Failed to load dokter');
  }
}

class Dokter {
  final String username;
  final String nama;
  final int tarif;

  const Dokter(
      {required this.username, required this.nama, required this.tarif});

  factory Dokter.fromJson(Map<String, dynamic> json) {
    return Dokter(
      username: json['username_dokter'],
      nama: json['nama_dokter'],
      tarif: json['tarif'],
    );
  }
}
