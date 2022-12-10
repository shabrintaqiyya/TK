import 'package:flutter/material.dart';
import 'package:google_fonts/google_fonts.dart';


class RoundedInputField extends StatefulWidget {
  final String text;
  var input;
  final Color color, textColor;
  final TextInputType inputType;
  RoundedInputField({
    Key? key,
    required this.text,
    required this.inputType,
    required this.input,
    this.color = const Color(0xFF1f8cce),
    this.textColor = Colors.white,
  }) : super(key: key);

  @override
  _RoundedInputFieldState createState() => _RoundedInputFieldState();
}

class _RoundedInputFieldState extends State<RoundedInputField> {
  @override
  Widget build(BuildContext context) {
    return Column(
      crossAxisAlignment: CrossAxisAlignment.start,
      children: <Widget>[
        Text(
          widget.text,
          style: GoogleFonts.poppins(
            color: Colors.black,
            fontSize: 16,
            fontWeight: FontWeight.normal)
        ),
        SizedBox(height: 10),
        Container(
          alignment: Alignment.centerLeft,
          decoration: BoxDecoration(
            color: Colors.white,
            borderRadius: BorderRadius.circular(25),
            boxShadow: [
              BoxShadow(
                color: Colors.black26,
                blurRadius: 3,
                offset: Offset(0,2)
              )
            ]
          ),
          height: 55,
          child: TextFormField(
            keyboardType: widget.inputType,
            onChanged: (String? value) {
                setState(() {
                    widget.input = value!;
                });
            },
            autovalidateMode: AutovalidateMode.onUserInteraction,
            validator: (value) {
              if (value!.isEmpty) {
                return 'Field tidak boleh kosong';
              }
              return null;
            },
            style: GoogleFonts.poppins(
              color: Colors.black87
            ),
            decoration: InputDecoration(
              border: InputBorder.none,
              contentPadding: EdgeInsets.only(top: 1, left: 10),
              hintText: widget.text,
              hintStyle: GoogleFonts.poppins(
                color: Colors.black38,
              )
            ),
          ),
        )
      ]
    );

  }
}