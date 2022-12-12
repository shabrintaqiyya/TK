import 'package:flutter/material.dart';
import 'package:rumahsehat_flutter/screens/appointment/create_apt_form.dart';

class DropdownDokter extends StatefulWidget {
  Function callback;
  Future<List<Dokter>> listDokter;
  DropdownDokter({key, required this.callback, required this.listDokter});

  @override
  State<DropdownDokter> createState() => _DropdownDokterState();
}

class _DropdownDokterState extends State<DropdownDokter> {
  String? dropdownValue;

  @override
  Widget build(BuildContext context) {
    return Container(
        margin: const EdgeInsets.only(top: 10),
        child: FutureBuilder<List<Dokter>>(
            future: widget.listDokter,
            builder: (context, snapshot) {
              if (snapshot.hasData) {
                return DropdownButton<String>(
                  hint: const SizedBox(
                      width: 150,
                      child: const Padding(
                        padding: const EdgeInsets.only(left: 16),
                        child: const Text(
                          "Pilih Dokter",
                          style: TextStyle(color: Colors.grey),
                        ),
                      )),
                  value: dropdownValue,
                  icon: const Icon(Icons.arrow_downward),
                  elevation: 16,
                  isExpanded: true,
                  style: const TextStyle(color: Colors.blue),
                  underline: Container(
                    height: 2,
                    color: Colors.blue,
                  ),
                  onChanged: (String? value) {
                    // This is called when the user selects an item.
                    setState(() {
                      dropdownValue = value!;
                    });
                    widget.callback(value!);
                  },
                  items: snapshot.data!
                      .map<DropdownMenuItem<String>>((Dokter value) {
                    return DropdownMenuItem<String>(
                        child:
                            Text(value.nama + " - " + value.tarif.toString()),
                        value: value.username);
                  }).toList(),
                );
              } else if (snapshot.hasError) {
                return Text('${snapshot.error}');
              } else {
                return const CircularProgressIndicator();
              }
            }));
  }
}
