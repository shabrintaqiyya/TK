import 'package:flutter/material.dart';
import 'package:flutter_datetime_picker/flutter_datetime_picker.dart';

class DateTimeFormat extends StatefulWidget {
  Function callback;
  DateTimeFormat({key, required this.callback});

  @override
  State<DateTimeFormat> createState() => _DateTimeFormatState();
}

class _DateTimeFormatState extends State<DateTimeFormat> {
  String _datetime = "Not set";

  @override
  Widget build(BuildContext context) {
    return ElevatedButton(
      onPressed: () {
        DatePicker.showDateTimePicker(context,
            theme: const DatePickerTheme(
              containerHeight: 250.0,
            ),
            showTitleActions: true, onConfirm: (datetime) {
          print('confirm $datetime');
          _datetime =
              '${datetime.year} - ${datetime.month} - ${datetime.day} ${datetime.hour} : ${datetime.minute}';
          setState(() {});
          widget.callback(datetime);
        }, currentTime: DateTime.now(), locale: LocaleType.en);
        setState(() {});
      },
      child: Container(
        alignment: Alignment.center,
        height: 50.0,
        child: Row(
          mainAxisAlignment: MainAxisAlignment.spaceBetween,
          children: <Widget>[
            Row(
              children: <Widget>[
                Row(
                  children: <Widget>[
                    const Icon(
                      Icons.access_time,
                      size: 18.0,
                      color: Colors.white,
                    ),
                    Text(
                      " $_datetime",
                      style: const TextStyle(
                          color: Colors.white,
                          fontWeight: FontWeight.bold,
                          fontSize: 18.0),
                    ),
                  ],
                )
              ],
            ),
            const Text(
              "  Change",
              style: TextStyle(
                  color: Colors.white,
                  fontWeight: FontWeight.bold,
                  fontSize: 18.0),
            ),
          ],
        ),
      ),
    );
  }
}
