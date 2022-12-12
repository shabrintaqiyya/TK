import 'package:flutter/material.dart';
import 'package:google_fonts/google_fonts.dart';


class RoundedButton extends StatelessWidget {
  final String text;
  final Color color, textColor;
  final VoidCallback press;
  const RoundedButton({
    Key? key,
    required this.text, required this.press,
    this.color = const Color(0xffA78BFA),
    this.textColor = Colors.white,
  }) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Container(
      padding: EdgeInsets.symmetric(vertical: 25),
      width: double.infinity,
      child: ElevatedButton(
        style: ElevatedButton.styleFrom(
          elevation: 5,
          padding: EdgeInsets.all(15),
          shape: RoundedRectangleBorder(
              borderRadius: BorderRadius.circular(25)
          ),
          primary: const Color(0xFF1f8cce),
        ),

        onPressed: press,
        // padding: EdgeInsets.all(15),
        // shape: RoundedRectangleBorder(
        //   borderRadius: BorderRadius.circular(25)
        // ),
        // color: Color(0xffA78BFA),
        child: Text(
          text,
          style: GoogleFonts.poppins(
            color: Colors.white,
            fontSize: 18,
            fontWeight: FontWeight.bold
          )
        ),
      ),
    );
  }
}