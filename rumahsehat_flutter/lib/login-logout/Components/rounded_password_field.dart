import 'package:flutter/material.dart';
import 'package:google_fonts/google_fonts.dart';


class RoundedPasswordField extends StatefulWidget {
  final String text;
  final Color color, textColor;
  var input;
  RoundedPasswordField({
    Key? key,
    required this.text,
    required this.input,
    this.color = const Color(0xffA78BFA),
    this.textColor = Colors.white,
  }) : super(key: key);

  @override
  _RoundedPasswordFieldState createState() => _RoundedPasswordFieldState();
}

class _RoundedPasswordFieldState extends State<RoundedPasswordField> {
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
            fontWeight: FontWeight.normal
          ),
          // TextStyle(
          //   color: Colors.black,
          //   fontSize: 16,
          //   fontWeight: FontWeight.normal
          // ),
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
            obscureText: true,
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
            // TextStyle(
            // ),
            decoration: InputDecoration(
              border: InputBorder.none,
              contentPadding: EdgeInsets.only(top: 1, left: 10),
              hintText: 'Password',
              hintStyle: GoogleFonts.poppins(
                color: Colors.black38,
              ),
              // TextStyle(
              // )
            ),
          ),
        )
      ]
    );
  }
}