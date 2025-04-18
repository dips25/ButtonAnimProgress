Welcome to the ButtonAnimProgress wiki!

### Add dependency

implementation "com.github.dips25:ButtonAnimProgress:v1.1"

### Add in layout

`* <com.example.buttonprogressbar.ButtonProgress`
*         `android:layout_width="match_parent"`
*         `android:layout_height="50dp"`
*         `app:layout_constraintTop_toTopOf="parent"`
*         `android:layout_margin="20dp"`
*         `custom:text="Click"`
*         `custom:textColor="@color/white"`
*         `custom:textSize="12"`
*         `custom:backgroundColor="@android:color/holo_green_dark"`
*         `custom:progressBackground="@android:color/holo_blue_dark"`
*         `custom:drawable="@drawable/gradient_drawable"`
*         `custom:typeface="@font/font_default"`
*         `custom:duration="300"`
*         `android:id="@+id/button_progress"/>`


### Start and stop accordingly

 `var bp: ButtonProgress = findViewById(com.example.buttonprogressbar.R.id.button_progress);`

        `bp.setOnClickListener {`
            
            `//start Animation`
            `bp.setAnim(Anim.RESUME)`

            `Handler().postDelayed(object:Runnable{`

                `override fun run() {`
                    
                    `//stop Animation`
                    `bp.setAnim(Anim.STOP)`
                `}`


            `},2000)`
        `}`

### Modify

        `custom:text="Click"`
        `custom:textColor="@color/white"`
        `custom:textSize="12"`
        `custom:backgroundColor="@android:color/holo_green_dark"`
        `custom:progressBackground="@android:color/holo_blue_dark"`
        `custom:drawable="@drawable/gradient_drawable"`
        `custom:typeface="@font/font_default"`
        `custom:duration="300"`

        ![Screen_recording_20250418_082531](https://github.com/user-attachments/assets/852eb6d2-1e5d-4c95-9ca8-ed7c38c02423)
